package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

            case "role":
                searchResult = repository.findByRoleContainedInSearchedRole(searchedFor);
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
                Integer phone;
                try {
                    phone = Integer.parseInt(searchedFor);
                    searchResult = repository.findByPhone(phone);
                }
                catch (Exception e) {

                }

                break;

            case "address":
                searchResult = repository.findByAddressContainedInSearchedAddress(searchedFor);
                break;

            case "country":
                searchResult = repository.findByCountryContainedInSearchedCountry(searchedFor);
                break;

            case "post code":
                searchResult = repository.findByPostCode(Integer.parseInt(searchedFor));
                break;

            case "sign up date":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date;
                try {
                    date = formatter.parse(searchedFor);
                    long day = date.getTime();
                    searchResult = repository.findBySignUpDateBetween(new Date(day), new Date(day + 86399999));
                }
                catch (ParseException e) {

                }

                break;

            case "active":
                searchResult = repository.findByActiveAccount(Boolean.valueOf(searchedFor));
                break;

            case "deactived since":
                formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = formatter.parse(searchedFor);
                    long day = date.getTime();
                    searchResult = repository.findByDeactivatedSinceBetween(new Date(day), new Date(day + 86399999));
                }
                catch (ParseException e) {

                }

                break;
        }

        model.addAttribute("users", searchResult);

        return "administration/users";
    }

}
