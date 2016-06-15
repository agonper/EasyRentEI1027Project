package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Photo;
import es.uji.daal.easyrent.model.Property;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.PhotoRepository;
import es.uji.daal.easyrent.validators.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 9/05/16.
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoRepository repository;

    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser != null) {
            model.addAttribute("photo", null);
            return "photo/add";
        }
        return "redirect:../login.html";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(BindingResult bindingResult, HttpSession session, Photo photo) {

        User loggedUser = (User) session.getAttribute("user");

        if (bindingResult.hasErrors() || loggedUser == null)
            return "photo/add";

        repository.save(photo);
        return "redirect:list.html";
    }
}
