package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.model.UserRole;
import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 27/04/2016.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = (List<User>) repository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/add";
        }
        user.setRole(UserRole.TENANT);
        user.setSignUpDate(new java.sql.Date(new Date().getTime()));
        user.setActive(false);
        repository.save(user);
        return "redirect:list.html";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable String id) {
        UUID userId = UUID.fromString(id);
        if (repository.exists(userId)) {
            repository.delete(userId);
        }
        return "redirect:../list.html";
    }
}
