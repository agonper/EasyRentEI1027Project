package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.model.UserRole;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = (List<User>) repository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/profile/{id}")
    public String show(Model model, @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        model.addAttribute("user", user);
        return "user/profile";
    }

    // FIXME Esto a la ruta de administracion
    @RequestMapping("/profile/{id}/changeState")
    public String changeState(@PathVariable String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.getRole() == UserRole.ADMINISTRATOR) {
            User user = repository.findOne(UUID.fromString(id));
            if (!user.getId().equals(loggedUser.getId())) {
                if (user.isActive())
                    user.setActive(false);
                else
                    user.setActive(true);
            }
            repository.save(user);
        }
        return "/user/profile";
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
        user.setPassword(passwordEncryptor.generateHash(user.getPassword()));
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
