package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.UserDAO;
import es.uji.daal.easyrent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Alberto on 27/04/2016.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
}
