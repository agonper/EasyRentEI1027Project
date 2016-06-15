package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.Property;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.repository.PropertyRepository;
import es.uji.daal.easyrent.utils.DateUtils;
import es.uji.daal.easyrent.validators.AddressInfoValidator;
import es.uji.daal.easyrent.validators.BookingValidator;
import es.uji.daal.easyrent.validators.PersonalDataValidator;
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
import java.util.*;

/**
 * Created by Alberto on 15/06/2016.
 */
@Controller
@RequestMapping("/property/booking-proposal/{id}")
public class BookPropertyFlowController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BookingProposalRepository proposalRepository;

    private enum BookStep {
        PERSONAL_DATA, ADDRESS_INFO, BOOKING_PROPOSAL, CHECK, PAY
    }

    @ModelAttribute("steps")
    public List getBookSteps() {
        String[][] steps = {
                {"user", "profile.personal-data"},
                {"pencil", "profile.address-info"},
                {"calendar", "proposal.title"},
                {"ok", "general.check"},
                {"credit-card", "proposal.pay"}
        };
        return Arrays.asList(steps);
    }

    @ModelAttribute("property")
    public Property getProperty(@PathVariable("id") String id) {
        return propertyRepository.findOne(UUID.fromString(id));
    }

    @ModelAttribute("sessionMapName")
    public String getSessionMapName(@PathVariable("id") String id) {
        return "book"+id;
    }

    @RequestMapping(value = "")
    public String book(Model model,
                      @RequestParam(name = "step", defaultValue = "0") String step,
                      HttpSession session, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyRepository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }

        BookStep bookStep = getStepParam(step);
        initializeSessionVariables(session, property);
        Map<String, Object> bookProperty = (Map<String, Object>) session.getAttribute(getBookSessionAttrName(property));

        bookStep = fixStepIfNeeded(bookProperty, bookStep);

        switch (bookStep) {
            case PERSONAL_DATA:
                PersonalDataForm personalDataForm = bookProperty.containsKey("personalDataForm") ?
                        (PersonalDataForm) bookProperty.get("personalDataForm") :
                        new PersonalDataForm().fillUp(loggedUser);
                model.addAttribute("personalDataForm", personalDataForm);
                break;
            case ADDRESS_INFO:
                AddressInfoForm addressInfoForm = bookProperty.containsKey("addressInfoForm") ?
                        (AddressInfoForm) bookProperty.get("addressInfoForm") :
                        new AddressInfoForm().fillUp(loggedUser);
                model.addAttribute("addressInfoForm", addressInfoForm);
                break;
            case BOOKING_PROPOSAL:
                if (!bookProperty.containsKey("bookingForm")) {
                    bookProperty.put("bookingForm", new BookingForm());
                }
                model.addAttribute("bookingForm", bookProperty.get("bookingForm"));
                break;
            case CHECK:
            case PAY:
                model.addAttribute("bookingProposal", bookProperty.get("bookingProposal"));
                break;
            default:

        }
        return "property/book/"+bookStep.ordinal();
    }

    private BookStep getStepParam(String step) {
        int intStep;
        try {
            intStep = Integer.parseInt(step);
        } catch (NumberFormatException e) {
            intStep = 0;
        }
        return (intStep >= 0 && intStep < BookStep.values().length) ? BookStep.values()[intStep] : BookStep.PERSONAL_DATA;
    }

    private void initializeSessionVariables(HttpSession session, Property property) {
        if (session.getAttribute(getBookSessionAttrName(property)) == null) {
            Map<String, Object> bookPropertyMap = new HashMap<>();
            bookPropertyMap.put("step", BookStep.PERSONAL_DATA);
            session.setAttribute(getBookSessionAttrName(property), bookPropertyMap);
        }
    }

    private String getBookSessionAttrName(Property property) {
        return getSessionMapName(property.getId().toString());
    }

    private BookStep fixStepIfNeeded(Map<String, Object> bookProperty, BookStep requested) {
        BookStep sessionStep = (BookStep) bookProperty.get("step");
        if (requested.ordinal() > sessionStep.ordinal()) {
            return sessionStep;
        }
        return requested;
    }

    @RequestMapping(value = "/0", method = RequestMethod.POST)
    public String processBookSubmit(@ModelAttribute("personalDataForm") PersonalDataForm personalDataForm,
                                    BindingResult bindingResult,
                                    HttpSession session, @PathVariable("id") String propertyId) {
        PersonalDataValidator validator = new PersonalDataValidator();
        validator.validate(personalDataForm, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/book/0";

        personalDataForm.update(loggedUser);

        Map<String, Object> bookProperty = (Map<String, Object>) session.getAttribute(getSessionMapName(propertyId));
        bookProperty.put("personalDataForm", personalDataForm);
        bookProperty.replace("step", BookStep.PERSONAL_DATA, BookStep.ADDRESS_INFO);

        return "redirect:?step=1";
    }

    @RequestMapping(value = "/1", method = RequestMethod.POST)
    public String processBookSubmit(@ModelAttribute("addressInfoForm") AddressInfoForm addressInfoForm,
                                   BindingResult bindingResult,
                                   HttpSession session, @PathVariable("id") String propertyId) {
        AddressInfoValidator validator = new AddressInfoValidator();
        validator.validate(addressInfoForm, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors())
            return "property/book/1";

        addressInfoForm.update(loggedUser);

        Map<String, Object> bookProperty = (Map<String, Object>) session.getAttribute(getSessionMapName(propertyId));
        bookProperty.put("addressInfoForm", addressInfoForm);
        bookProperty.replace("step", BookStep.ADDRESS_INFO, BookStep.BOOKING_PROPOSAL);

        return "redirect:?step=2";
    }

    @RequestMapping(value = "/2", method = RequestMethod.POST)
    public String processBookingProposal(@ModelAttribute BookingForm bookingForm,
                                         BindingResult bindingResult,
                                         @PathVariable("id") String propertyId,
                                         HttpSession session) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyRepository.findOne(UUID.fromString(propertyId));

        new BookingValidator(property.getAvailabilityPeriods()).validate(bookingForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "property/book/2";
        }

        BookingProposal proposal = bookingForm.update(property.createBookingProposal(loggedUser));
        proposal.setTotalAmount(property.getPricePerDay() *
                DateUtils.daysBetweenDates(bookingForm.getEndDate(), bookingForm.getStartDate()));

        Map<String, Object> bookProperty = (Map<String, Object>) session.getAttribute(getSessionMapName(propertyId));
        bookProperty.put("bookingForm", bookingForm);
        bookProperty.put("bookingProposal", proposal);
        bookProperty.replace("step", BookStep.BOOKING_PROPOSAL, BookStep.CHECK);

        return "redirect:?step=3";
    }

}
