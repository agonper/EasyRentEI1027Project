package es.uji.daal.easyrent.controller;

import javax.servlet.http.HttpSession;

import es.uji.daal.easyrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daniel on 30/04/16.
 */

@Controller
public class AuthController {

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index.html";
    }
}
