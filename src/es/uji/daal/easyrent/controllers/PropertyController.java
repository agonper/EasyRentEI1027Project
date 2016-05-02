package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.PropertyDAO;
import es.uji.daal.easyrent.models.Property;

import es.uji.daal.easyrent.models.User;
import es.uji.daal.easyrent.validators.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    PropertyDAO propertyDAO;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Property> properties = propertyDAO.findAll();
        model.addAttribute("properties", properties);
        return "property/list";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("property", new Property());
        return "property/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult, HttpSession session) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        User loggedUser = (User) session.getAttribute("user");

        if (bindingResult.hasErrors() || loggedUser == null)
            return "property/add";
        
        property.setCreationDate(new Date(System.currentTimeMillis()));
        property.setOwnerID(loggedUser.getId());
        propertyDAO.storeRecord(property);
        return "redirect:list.html";
    }

    //TODO: Revisar el nombre de 'update' a posteriori
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("property", propertyDAO.findOneByID(UUID.fromString(id)));
        return "property/update";
    }

    //TODO: Revisar validez del ID
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String processUpdateSubmit(//@PathVariable(value = "id") String id,
                                      @ModelAttribute("property") Property property, BindingResult bindingResult) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        if (bindingResult.hasErrors())
            return "property/update";
        propertyDAO.updateRecord(property);
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {
        propertyDAO.destroyRecord(propertyDAO.findOneByID(UUID.fromString(id)));
        return "redirect:../list.html";
    }
}
