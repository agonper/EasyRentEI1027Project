package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.Property;

import es.uji.daal.easyrent.model.PropertyType;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.repository.PropertyRepository;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.utils.DateUtils;
import es.uji.daal.easyrent.validators.AddressInfoValidator;
import es.uji.daal.easyrent.validators.BookingValidator;
import es.uji.daal.easyrent.validators.PersonalDataValidator;
import es.uji.daal.easyrent.validators.PropertyValidator;
import es.uji.daal.easyrent.view_models.AddressInfoForm;
import es.uji.daal.easyrent.view_models.BookingForm;
import es.uji.daal.easyrent.view_models.PersonalDataForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.*;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingProposalRepository proposalRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Property> properties = (List<Property>) repository.findAll();
        model.addAttribute("properties", properties);
        return "property/list";
    }

    @RequestMapping("/listOwnProperties")
    public String listOwnProperties(Model model) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser != null) {
            UUID userID = loggedUser.getId();
            List<Property> userProperties = repository.findByOwner_Id(userID);
            model.addAttribute("userProperties", userProperties);
            return "property/listOwnProperties";
        }
        return "redirect:../login.html";
    }

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Property property = repository.findOne(UUID.fromString(id));
        model.addAttribute("property", property);
        return "property/show";
    }

    @ModelAttribute("steps")
    public List getAddSteps() {
        String[][] steps = {
                {"user", "profile.personal-data"},
                {"pencil", "profile.address-info"},
                {"home", "property.property-info"},
                {"calendar", "property.availability-dates"},
                {"picture", "property.photos"},
                {"ok", "general.check"}
        };
        return Arrays.asList(steps);
    }

    @ModelAttribute("propertyTypes")
    public List getPropertyTypes() {
        return Arrays.asList(PropertyType.values());
    }

    @RequestMapping(value = "/add")
    public String add(Model model, @RequestParam(name = "step", defaultValue = "0") String step, HttpSession session) {
        AddStep addStep = getStepParam(step);
        initializeSessionVariables(session);
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        addStep = fixstepIfNeeded(addProperty, addStep);

        switch (addStep) {
            case PERSONAL_DATA:
                PersonalDataForm personalDataForm = new PersonalDataForm();
                personalDataForm.fillUp(loggedUser);
                if (addProperty.containsKey("personalDataForm")) {
                    personalDataForm = (PersonalDataForm) addProperty.get("personalDataForm");
                }
                model.addAttribute("personalDataForm", personalDataForm);
                break;
            case ADDRESS_INFO:
                AddressInfoForm addressInfoForm = new AddressInfoForm();
                addressInfoForm.fillUp(loggedUser);
                if (addProperty.containsKey("addressInfoForm")) {
                    addressInfoForm = (AddressInfoForm) addProperty.get("addressInfoForm");
                }
                model.addAttribute("addressInfoForm", addressInfoForm);
                break;
            case PROPERTY_INFO:
                Property property = addProperty.containsKey("property") ?
                        (Property) addProperty.get("property") : loggedUser.createProperty();
                model.addAttribute("property", property);
                break;
            case AVAILABILITY_DATES:
                break;
            case PHOTOS:
                break;
            case CHECK:
                model.addAttribute("property", addProperty.get("property"));
                break;
            default:

        }
        return "property/add/"+addStep.ordinal();
    }

    private AddStep getStepParam(String step) {
        int intStep;
        try {
            intStep = Integer.parseInt(step);
        } catch (NumberFormatException e) {
            intStep = 0;
        }
        return (intStep >= 0 && intStep < 6) ? AddStep.values()[intStep] : AddStep.PERSONAL_DATA;
    }

    private void initializeSessionVariables(HttpSession session) {
        if (session.getAttribute("addPropertyMap") == null) {
            Map<String, Object> addPropertyMap = new HashMap<>();
            addPropertyMap.put("step", AddStep.PERSONAL_DATA);
            session.setAttribute("addPropertyMap", addPropertyMap);
        }
    }

    private AddStep fixstepIfNeeded(Map<String, Object> addProperty, AddStep requested) {
        AddStep sessionStep = (AddStep) addProperty.get("step");
        if (requested.ordinal() > sessionStep.ordinal()) {
            return sessionStep;
        }
        return requested;
    }

    @RequestMapping(value = "/add/0", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("personalDataForm") PersonalDataForm personalDataForm,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        PersonalDataValidator validator = new PersonalDataValidator();
        validator.validate(personalDataForm, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/add/0";

        personalDataForm.update(loggedUser);

        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.put("personalDataForm", personalDataForm);
        addProperty.replace("step", AddStep.PERSONAL_DATA, AddStep.ADDRESS_INFO);

        return "redirect:?step=1";
    }

    @RequestMapping(value = "/add/1", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("addressInfoForm") AddressInfoForm addressInfoForm,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        AddressInfoValidator validator = new AddressInfoValidator();
        validator.validate(addressInfoForm, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/add/1";

        addressInfoForm.update(loggedUser);

        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.put("addressInfoForm", addressInfoForm);
        addProperty.replace("step", AddStep.ADDRESS_INFO, AddStep.PROPERTY_INFO);

        return "redirect:?step=2";
    }

    @RequestMapping(value = "/add/2", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/add/2";

        property.setCreationDate(new Date());
        property.setOwner(loggedUser);
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.put("property", property);
        addProperty.replace("step", AddStep.PROPERTY_INFO, AddStep.AVAILABILITY_DATES);
        return "redirect:?step=3";
    }

    @RequestMapping(value = "/add/3", method = RequestMethod.POST)
    public String processAddSubmit(HttpSession session) {
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.replace("step", AddStep.AVAILABILITY_DATES, AddStep.PHOTOS);
        return "redirect:?step=4";
    }

    @RequestMapping(value = "/add/4", method = RequestMethod.POST)
    public String processAddPhotosSubmit(HttpSession session) {
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.replace("step", AddStep.PHOTOS, AddStep.CHECK);
        return "redirect:?step=5";
    }

    @RequestMapping(value = "/add/5", method = RequestMethod.POST)
    public String processAddCheckSubmit(HttpSession session) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");

        PersonalDataForm personalDataForm = (PersonalDataForm) addProperty.get("personalDataForm");
        AddressInfoForm addressInfoForm = (AddressInfoForm) addProperty.get("addressInfoForm");
        personalDataForm.update(loggedUser);
        addressInfoForm.update(loggedUser);
        userRepository.save(loggedUser);

        Property property = (Property) addProperty.get("property");
        repository.save(property);

        session.removeAttribute("addPropertyMap");

        return "redirect:../../user/owner/"+ loggedUser.getId() +".html";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("property", repository.findOne(UUID.fromString(id)));
        return "property/update";
    }

    //TODO: Revisar validez del ID
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("property") Property property, BindingResult bindingResult) {
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);

        if (bindingResult.hasErrors())
            return "property/update";

        repository.save(property);
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {
        UUID propertyId = UUID.fromString(id);
        if (repository.exists(propertyId)) {
            repository.delete(propertyId);
        }
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/booking-proposal/{id}")
    public String bookingProposal(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = repository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }
        model.addAttribute("property", property);
        model.addAttribute("bookingForm", new BookingForm());
        return "bookingProposal/add";
    }

    @RequestMapping(value = "/booking-proposal/{id}", method = RequestMethod.POST)
    public String processBookingProposal(@ModelAttribute BookingForm bookingForm,
                                         @PathVariable("id") String id,
                                         BindingResult bindingResult) {
        System.out.println("Got it!");
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = repository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }
        // TODO improve form checking
        System.out.println("Validating...");
        new BookingValidator().validate(bookingForm, bindingResult);
        System.out.println("Validated");
        if (bindingResult.hasErrors()) {
            return "bookingProposal/add";
        }
        BookingProposal proposal = bookingForm.update(property.createBookingProposal(loggedUser));
        proposal.setTotalAmount(bookingForm.getNumberOfTenants() *
                property.getPricePerDay() *
                DateUtils.daysBetweenDates(bookingForm.getEndDate(), bookingForm.getStartDate()));
        proposalRepository.save(proposal);
        return "redirect:../../user/tenant/"+loggedUser.getId()+".html";
    }

    private enum AddStep {
        PERSONAL_DATA, ADDRESS_INFO, PROPERTY_INFO, AVAILABILITY_DATES, PHOTOS, CHECK
    }
}
