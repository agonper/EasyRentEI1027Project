package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.handler.ContactEmailBroker;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
import es.uji.daal.easyrent.repository.InvoiceRepository;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.validators.ContactFormValidator;
import es.uji.daal.easyrent.view_models.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    private ContactEmailBroker emailBroker;

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
            model.addAttribute("invoices", invoiceRepository.findByProposal_Tenant_IdOrderByNumberAsc(loggedUser.getId()));
            model.addAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping("/become-a-host")
    private String becomeAHost() {
        return "index/becomeAHost";
    }

    @RequestMapping("/about-us")
    private String aboutUs() {
        return "index/aboutUs";
    }

    @RequestMapping(value = "/contact-us")
    private String contactUs(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "index/contactUs";
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.POST)
    private String processContactUs(@ModelAttribute("contactForm") ContactForm contactForm,
                                    BindingResult bindingResult) {
        new ContactFormValidator().validate(contactForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "index/contactUs";
        }
        emailBroker.setForm(contactForm).sendContactEmail();
        return  "redirect:contact-us?success=t";
    }
}
