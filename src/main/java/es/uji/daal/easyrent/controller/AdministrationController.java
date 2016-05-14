package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 14/05/16.
 */
@Controller
@RequestMapping("/administration")
public class AdministrationController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = { "/", ""})
    public String root() {
        return "administration/root";
    }

    @RequestMapping("/searchUsers")
    public String searchUsers(Model model, @RequestParam("searchBy") String searchBy, @RequestParam("inputValue") String input, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "administration/searchUsers";

        List<User> queryResult = new LinkedList<>();
        switch (searchBy) {
            case "ID":
                queryResult.add(userRepository.findOne(UUID.fromString(input)));
                break;

            case "username":
                queryResult.add(userRepository.findByUsernameIgnoreCase(input));
                break;
        }
        model.addAttribute("users", queryResult);
        return "administration/searchUsers";
    }
}
