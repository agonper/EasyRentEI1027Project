package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 6/06/16.
 */

@Controller
@RequestMapping("/administration/users")
public class AdministrationUserSearchController {

    @Autowired
    UserRepository repository;

    @RequestMapping("/searchFor")
    public String searchFor(@RequestParam String searchedFor, @RequestParam String selectedUserAttribute, Model model) {

        List<User> searchResult = new LinkedList<>();

        switch (selectedUserAttribute) {

            case "username":
                searchResult = repository.findByUsernameContainedInSearchedName(searchedFor);
                break;

            case "ID":

                break;

            case "role":

                break;

            case "NID":
                searchResult = repository.findByNIDContainedInSearchedNID(searchedFor);
                break;

            case "name":
                searchResult = repository.findByNameContainedInSearchedName(searchedFor);
                break;

            case "surnames":
                searchResult = repository.findBySurnamesContainedInSearchedSurnames(searchedFor);
                break;

            case "email":
                searchResult = repository.findByEmailContainedInSearchedEmails(searchedFor);
                break;

            case "phone number":

                break;

            case "address":
                searchResult = repository.findByAddressContainedInSearchedAddress(searchedFor);
                break;

            case "country":
                searchResult = repository.findByCountryContainedInSearchedCountry(searchedFor);
                break;

            case "post code":

                break;

            case "sign up date":

                break;

            case "active":

                break;

            case "deactived since":

                break;
        }

        model.addAttribute("users", searchResult);

        return "administration/users";
    }

}
