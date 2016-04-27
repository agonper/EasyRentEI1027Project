package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.DAO;
import es.uji.daal.easyrent.daos.PropertyDAO;
import es.uji.daal.easyrent.models.Property;

import es.uji.daal.easyrent.validators.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping("/property")
public class PropertyController {

    private PropertyDAO propertyDAO;

    //TODO: Revisar si la conversión es necesaria
    @Autowired
    public void setPropertyDAO(DAO propertyDAO) {
        this.propertyDAO = (PropertyDAO)propertyDAO;
    }

    @RequestMapping("/list")
    public String listProperties(Model model) {
        model.addAttribute("properties", propertyDAO.findAll());
        return "property/list";
    }

    @RequestMapping("/add")
    public String addProperty(Model model) {
        model.addAttribute("property", new Property());
        return "property/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        if (bindingResult.hasErrors())
            return "property/add";
        propertyDAO.storeRecord(property);
        return "redirect:list.html";
    }

    //TODO: Revisar el nombre de 'update' a posteriori
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String editProperty(Model model, @PathVariable(value = "id") String id) {
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
