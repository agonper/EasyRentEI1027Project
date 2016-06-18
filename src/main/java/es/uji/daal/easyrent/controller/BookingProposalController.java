package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.handler.BookingProposalEmailBroker;
import es.uji.daal.easyrent.model.*;
import es.uji.daal.easyrent.repository.AvailabilityPeriodRepository;
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

    @Autowired
    private AvailabilityPeriodRepository periodRepository;

    @Autowired
    private BookingProposalEmailBroker emailBroker;

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
    public String reject(@PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingProposal proposal = repository.findOne(UUID.fromString(id));
        if (loggedUser.equals(proposal.getProperty().getOwner()) && proposal.getStatus() == ProposalStatus.PENDING) {
            proposal.reject();
            repository.save(proposal);

            AvailabilityPeriod period = proposal.getProperty().createAvailabilityPeriod();
            period.setStartDate(proposal.getStartDate());
            period.setEndDate(proposal.getEndDate());
            periodRepository.save(period);

            emailBroker
                    .setProposal(proposal)
                    .sendTenantRejectionEmail();

            return "redirect:../../index.html#owner-proposals";
        }
        return "redirect:../../index.html";
    }

    @RequestMapping("/accept/{id}")
    public String accept(@PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookingProposal proposal = repository.findOne(UUID.fromString(id));
        if (loggedUser.equals(proposal.getProperty().getOwner()) && proposal.getStatus() == ProposalStatus.PENDING) {
            proposal.accept();
            Invoice invoice = proposal.createInvoice();

            User tenant = proposal.getTenant();
            invoice.setAddress(tenant.getPostalAddress());
            invoice.setCountry(tenant.getCountry());
            invoice.setPostCode(tenant.getPostCode());

            repository.save(proposal);

            emailBroker
                    .setProposal(proposal)
                    .sendTenantAcceptationEmail();
            return "redirect:../../index.html#owner-proposals";
        }
        return "redirect:../../index.html";
    }
}
