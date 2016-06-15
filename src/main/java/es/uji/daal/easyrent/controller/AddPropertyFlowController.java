package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.*;
import es.uji.daal.easyrent.repository.*;
import es.uji.daal.easyrent.validators.AddressInfoValidator;
import es.uji.daal.easyrent.validators.PersonalDataValidator;
import es.uji.daal.easyrent.validators.PropertyValidator;
import es.uji.daal.easyrent.view_models.AddressInfoForm;
import es.uji.daal.easyrent.view_models.AvailabilityForm;
import es.uji.daal.easyrent.view_models.PersonalDataForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alberto on 14/06/2016.
 */

@Controller
@RequestMapping("/property/add")
public class AddPropertyFlowController {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AvailabilityPeriodRepository availabilityRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PhotoRepository photoRepository;

    private enum AddStep {
        PERSONAL_DATA, ADDRESS_INFO, PROPERTY_INFO, AVAILABILITY_DATES, SERVICES, PHOTOS, CHECK
    }

    @ModelAttribute("steps")
    public List getAddSteps() {
        String[][] steps = {
                {"user", "profile.personal-data"},
                {"pencil", "profile.address-info"},
                {"home", "property.property-info"},
                {"calendar", "property.availability-dates"},
                {"cutlery", "property.services"},
                {"picture", "property.photos"},
                {"ok", "general.check"}
        };
        return Arrays.asList(steps);
    }

    @ModelAttribute("propertyTypes")
    public List getPropertyTypes() {
        return Arrays.asList(PropertyType.values());
    }

    @RequestMapping(value = "")
    public String add(Model model, @RequestParam(name = "step", defaultValue = "0") String step, HttpSession session) {
        AddStep addStep = getStepParam(step);
        initializeSessionVariables(session);
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        addStep = fixStepIfNeeded(addProperty, addStep);

        switch (addStep) {
            case PERSONAL_DATA:
                PersonalDataForm personalDataForm = addProperty.containsKey("personalDataForm") ?
                        (PersonalDataForm) addProperty.get("personalDataForm") :
                        new PersonalDataForm().fillUp(loggedUser);
                model.addAttribute("personalDataForm", personalDataForm);
                break;
            case ADDRESS_INFO:
                AddressInfoForm addressInfoForm = addProperty.containsKey("addressInfoForm") ?
                        (AddressInfoForm) addProperty.get("addressInfoForm") :
                        new AddressInfoForm().fillUp(loggedUser);
                model.addAttribute("addressInfoForm", addressInfoForm);
                break;
            case PROPERTY_INFO:
                Property property = addProperty.containsKey("property") ?
                        (Property) addProperty.get("property") : loggedUser.createProperty();
                model.addAttribute("property", property);
                break;
            case AVAILABILITY_DATES:
                if (!addProperty.containsKey("availabilityPeriods")) {
                    addProperty.put("availabilityPeriods", new ArrayList<AvailabilityForm>());
                }
                model.addAttribute("availabilityPeriods", addProperty.get("availabilityPeriods"));
                model.addAttribute("availabilityForm", new AvailabilityForm());
                break;
            case SERVICES:
                if (!addProperty.containsKey("services")) {
                    addProperty.put("services", new ArrayList<Service>());
                }
                model.addAttribute("services", addProperty.get("services"));
                break;
            case PHOTOS:
                if (!addProperty.containsKey("propertyPhotos")) {
                    addProperty.put("propertyPhotos", new HashSet<String>());
                }
                model.addAttribute("propertyPhotos", addProperty.get("propertyPhotos"));
                break;
            case CHECK:
                model.addAttribute("availabilityPeriods", addProperty.get("availabilityPeriods"));
                model.addAttribute("services", addProperty.get("services"));
                model.addAttribute("photos", addProperty.get("photos"));
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
        return (intStep >= 0 && intStep < AddStep.values().length) ? AddStep.values()[intStep] : AddStep.PERSONAL_DATA;
    }

    private void initializeSessionVariables(HttpSession session) {
        if (session.getAttribute("addPropertyMap") == null) {
            Map<String, Object> addPropertyMap = new HashMap<>();
            addPropertyMap.put("step", AddStep.PERSONAL_DATA);
            session.setAttribute("addPropertyMap", addPropertyMap);
        }
    }

    private AddStep fixStepIfNeeded(Map<String, Object> addProperty, AddStep requested) {
        AddStep sessionStep = (AddStep) addProperty.get("step");
        if (requested.ordinal() > sessionStep.ordinal()) {
            return sessionStep;
        }
        return requested;
    }

    @RequestMapping(value = "/0", method = RequestMethod.POST)
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

    @RequestMapping(value = "/1", method = RequestMethod.POST)
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

    @RequestMapping(value = "/2", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/add/2";

        //FIXME: Crear un view model para esto, así no se ponen los campos a null
        property.setCreationDate(new Date());
        property.setOwner(loggedUser);
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        addProperty.put("property", property);
        addProperty.replace("step", AddStep.PROPERTY_INFO, AddStep.AVAILABILITY_DATES);
        return "redirect:?step=3";
    }

    @RequestMapping(value = "/3", method = RequestMethod.POST)
    public String processAddSubmit(HttpSession session) {
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        List<AvailabilityForm> availabilityForms = (List<AvailabilityForm>) addProperty.get("availabilityPeriods");
        Property property = (Property) addProperty.get("property");

        List<AvailabilityPeriod> availabilityPeriods = availabilityForms.stream()
                .map(form -> form.update(property.createAvailabilityPeriod())).collect(Collectors.toCollection(LinkedList::new));

        addProperty.put("availabilities", availabilityPeriods);
        addProperty.replace("step", AddStep.AVAILABILITY_DATES, AddStep.SERVICES);
        return "redirect:?step=4";
    }

    @RequestMapping(value = "/4", method = RequestMethod.POST)
    public String processAddServicesSubmit(HttpSession session) {
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        Property property = (Property) addProperty.get("property");

        addProperty.replace("step", AddStep.SERVICES, AddStep.PHOTOS);
        return "redirect:?step=5";
    }

    @RequestMapping(value = "/5", method = RequestMethod.POST)
    public String processAddPhotosSubmit(HttpSession session) {
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
        Set<String> propertyPhotos = (Set<String>) addProperty.get("propertyPhotos");
        Property property = (Property) addProperty.get("property");

        List<Photo> photos = propertyPhotos.stream()
                .map(property::createPhoto).collect(Collectors.toCollection(LinkedList::new));

        addProperty.put("photos", photos);
        addProperty.replace("step", AddStep.PHOTOS, AddStep.CHECK);
        return "redirect:?step=6";
    }

    @RequestMapping(value = "/6", method = RequestMethod.POST)
    public String processAddCheckSubmit(HttpSession session) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");

        PersonalDataForm personalDataForm = (PersonalDataForm) addProperty.get("personalDataForm");
        AddressInfoForm addressInfoForm = (AddressInfoForm) addProperty.get("addressInfoForm");
        personalDataForm.update(loggedUser);
        addressInfoForm.update(loggedUser);
        if (loggedUser.getRole() == UserRole.TENANT) {
            loggedUser.setRole(UserRole.OWNER);
        }
        userRepository.save(loggedUser);

        Property property = (Property) addProperty.get("property");
        List<Service> services = (List<Service>) addProperty.get("services");
        property.addServices(services);
        serviceRepository.save(services);
        repository.save(property);

        List<AvailabilityPeriod> periods = (List<AvailabilityPeriod>) addProperty.get("availabilities");
        availabilityRepository.save(periods);

        List<Photo> photos = (List<Photo>) addProperty.get("photos");
        photoRepository.save(photos);

        // FIXME Increment service usage

        session.removeAttribute("addPropertyMap");

        return "redirect:../../index.html#owner-properties";
    }
}
