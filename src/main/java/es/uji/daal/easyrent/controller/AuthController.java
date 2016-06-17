package es.uji.daal.easyrent.controller;

import javax.servlet.http.HttpSession;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.utils.PasswordEncryptor;
import es.uji.daal.easyrent.validators.SignupValidator;
import es.uji.daal.easyrent.view_models.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by daniel on 30/04/16.
 */

@Controller
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        SignupForm signupForm = new SignupForm();
        model.addAttribute("form", signupForm);
        return "auth/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processSignup(@ModelAttribute("form") SignupForm form,
                                BindingResult bindingResult) {
        new SignupValidator().validate(form, bindingResult);
        if (repository.existsByUsername(form.getUsername())) {
            bindingResult.rejectValue("username", "exists", "That username already exists");
        }
        if (repository.existsByEmail(form.getEmail())) {
            bindingResult.rejectValue("email", "exists", "There is an account with that email");
        }
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }
        form.setPassword(passwordEncryptor.generateHash(form.getPassword()));
        User user = form.update(new User());
        repository.save(user);
        return "redirect:index.html";
    }
}
