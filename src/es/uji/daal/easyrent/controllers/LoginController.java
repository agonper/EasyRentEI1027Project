package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.UserDAO;
import javax.servlet.http.HttpSession;

import es.uji.daal.easyrent.models.User;
import es.uji.daal.easyrent.validators.LoginUserValidator;
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
public class LoginController {
    @Autowired
    private UserDAO userDao;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") User user,
                             BindingResult bindingResult, HttpSession session) {
        LoginUserValidator loginUserValidator = new LoginUserValidator();
        loginUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }

        user = userDao.findByUserAndPassword(user.getUsername(), user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Bad password");
            return "login";
        }

        session.setAttribute("user", user);

        String next = (String)session.getAttribute("nextUrl");

        if (next != null) {
            session.setAttribute("nextUrl", null);
            return "redirect:" + next;
        }
        return "redirect:index.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index.jsp";
    }
}
