package es.uji.daal.easyrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by daniel on 14/05/16.
 */
@Controller
@RequestMapping("/administration")
public class AdministrationController {

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
    public String services() {
        return "administration/services";
    }
}
