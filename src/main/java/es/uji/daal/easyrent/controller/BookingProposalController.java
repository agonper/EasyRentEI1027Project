package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.Invoice;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * Created by Alberto on 17/05/2016.
 */

@Controller
@RequestMapping("/booking-proposal")
public class BookingProposalController {

    @Autowired
    private BookingProposalRepository repository;

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingProposal proposal = repository.findOne(UUID.fromString(id));
        if (loggedUser.equals(proposal.getTenant()) || loggedUser.equals(proposal.getProperty().getOwner())) {
            model.addAttribute("bookingProposal", proposal);
            return "bookingProposal/show";
        }
        return "redirect:../../index.html";
    }

    @RequestMapping("/reject/{id}")
    public String reject(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingProposal proposal = repository.findOne(UUID.fromString(id));
        if (loggedUser.equals(proposal.getProperty().getOwner())) {
            proposal.reject();
            repository.save(proposal);
            return "redirect:../../index.html#owner-proposals";
        }
        return "redirect:../../index.html";
    }

    @RequestMapping("/accept/{id}")
    public String accept(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingProposal proposal = repository.findOne(UUID.fromString(id));
        if (loggedUser.equals(proposal.getProperty().getOwner())) {
            proposal.accept();
            Invoice invoice = new Invoice();
            proposal.setInvoice(invoice);
            // FIXME please kill me
            invoice.setVat(0.21f);

            User tenant = proposal.getTenant();
            invoice.setAddress(tenant.getPostalAddress()+" "+tenant.getCountry()+" "+tenant.getPostCode());

            repository.save(proposal);
            return "redirect:../../index.html#owner-proposals";
        }
        return "redirect:../../index.html";
    }
}
