package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Service;
import es.uji.daal.easyrent.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel on 13/06/16.
 */

@Controller
@RequestMapping("/administration/services")
public class AdministrationServicesSearchController {

    @Autowired
    ServiceRepository repository;

    @RequestMapping("/searchFor")
    public String searchFor(@RequestParam String searchedFor, @RequestParam String selectedServiceAttribute, Model model) {

        List<Service> searchResult = new LinkedList<>();

        switch (selectedServiceAttribute) {

            case "name":
                searchResult = repository.searchByNameContainedInSearchedName(searchedFor);
                break;

            case "proposedByUser":
                searchResult = repository.searchByUserContainedInSearchedUser(searchedFor);
                break;

            case "active":
                Boolean active;
                try {
                    active = Boolean.valueOf(searchedFor);
                    searchResult = repository.findActive(active);
                }
                catch (NumberFormatException e) {

                }
                break;

            case "creationDate":
                Date creationDate;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    creationDate = formatter.parse(searchedFor);
                    searchResult = repository.findByCreationDate(creationDate);
                }
                catch (ParseException e) {

                }
                break;

            case "activeSince":
                Date activeSince;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    activeSince = formatter.parse(searchedFor);
                    searchResult = repository.findByActiveSince(activeSince);
                }
                catch (ParseException e) {

                }
                break;

            case "serviceProposals":
                Integer proposals;
                try {
                    proposals = Integer.getInteger(searchedFor);
                    searchResult = repository.findByServiceProposals(proposals);
                }
                catch (NumberFormatException e) {

                }
                break;
        }

        model.addAttribute("services", searchResult);

        return "administration/services";
    }
}
