package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.model.UserRole;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 12/05/16.
 */
@Controller
@RequestMapping("/user/bookingProposal")
public class BookingProposalController {
    @Autowired
    BookingProposalRepository repository;

    @RequestMapping("/list")
    public String list(Model model) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BookingProposal> bookingProposals;
        if (loggedUser.getRole() == UserRole.ADMINISTRATOR)
            bookingProposals = (List<BookingProposal>)repository.findAll();
        else
            bookingProposals = repository.findByTenant_Id(loggedUser.getId());
        model.addAttribute("bookingProposals", bookingProposals);
        return "/user/bookingProposal/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {

        UUID propertyId = UUID.fromString(id);
        if (repository.exists(propertyId)) {
            repository.delete(propertyId);
        }
        return "redirect:../list.html";
    }
}
