package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.repository.InvoiceRepository;
import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alberto on 05/05/2016.
 */
@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingProposalRepository proposalRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @RequestMapping("/")
    public String root(Model model) {
        return populateIndexModel(model);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return populateIndexModel(model);
    }

    private String populateIndexModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User) {
            User loggedUser = (User) authentication.getPrincipal();
            User user = userRepository.findOne(loggedUser.getId());
            model.addAttribute("bookingProposals", proposalRepository.findByProperty_Owner_IdOrderByDateOfCreationDesc(loggedUser.getId()));
            model.addAttribute("invoices", invoiceRepository.findByProposal_Tenant_IdOrderByExpeditionDateDesc(loggedUser.getId()));
            model.addAttribute("user", user);
        }
        return "index";
    }
}
