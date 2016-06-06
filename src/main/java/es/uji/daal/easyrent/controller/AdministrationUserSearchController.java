package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(searchedFor + " " + selectedUserAttribute);

        model.addAttribute("users", repository.findByUsernameLikeOrContains("pru"));


        return "administration/users";
    }

}
