package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Service;
import es.uji.daal.easyrent.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by daniel on 14/05/16.
 */
@Controller
@RequestMapping("/administration")
public class AdministrationController {

    @Autowired
    ServiceRepository repository;

    @RequestMapping(value = { "/", "", "/users"})
    public String users() {
        return "administration/users";
    }

    @RequestMapping("/properties")
    public String properties() {
        return "administration/properties";
    }

    @RequestMapping("/booking_proposals")
    public String bookingProposals() {
        return "administration/booking_proposals";
    }

    @RequestMapping("/invoices")
    public String invoices() {
        return "administration/invoices";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        List<Service> mostDemandedServices;
        mostDemandedServices = repository.findTop5MostDemandedServices(new PageRequest(0, 5));
        model.addAttribute("mostDemandedServices", mostDemandedServices);
        return "administration/services";
    }

    @ModelAttribute("numberOfServicesNotActive")
    public int numberOfServicesNotActive() {
        return repository.findNumberOfServicesNotActive();
    }
}
