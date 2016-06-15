package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.Property;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.repository.PropertyRepository;
import es.uji.daal.easyrent.utils.DateUtils;
import es.uji.daal.easyrent.validators.BookingValidator;
import es.uji.daal.easyrent.view_models.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by Alberto on 15/06/2016.
 */
@Controller
@RequestMapping("/property/booking-proposal")
public class BookPropertyFlowController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BookingProposalRepository proposalRepository;

    @RequestMapping(value = "/{id}")
    public String bookingProposal(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyRepository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }
        model.addAttribute("property", property);
        model.addAttribute("bookingForm", new BookingForm());
        return "bookingProposal/add";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String processBookingProposal(@ModelAttribute BookingForm bookingForm,
                                         @PathVariable("id") String id,
                                         BindingResult bindingResult) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyRepository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }

        new BookingValidator().validate(bookingForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "bookingProposal/add";
        }

        BookingProposal proposal = bookingForm.update(property.createBookingProposal(loggedUser));
        proposal.setTotalAmount(bookingForm.getNumberOfTenants() *
                property.getPricePerDay() *
                DateUtils.daysBetweenDates(bookingForm.getEndDate(), bookingForm.getStartDate()));
        proposalRepository.save(proposal);
        return "redirect:../../index.html#tenant";
    }

}
