package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.PropertyDAO;
import es.uji.daal.easyrent.models.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping()
public class PropertyController {

    private PropertyDAO propertyDAO;

    @Autowired
    public void setPropertyDAO(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    @RequestMapping()
    public String listProperties(Model model) {
        model.addAttribute("properties", propertyDAO.getProperties());
        return X;
    }

    @RequestMapping()
    public String addProperty(Model model) {
        model.addAttribute("property", new Property());
        return X;
    }

    @RequestMapping(value = Z, method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        if (bindingResult.hasErrors())
            return X;
        propertyDAO.addProperty(property);
        return X;
    }

    @RequestMapping(value = Z, method = RequestMethod.GET)
    public String editProperty(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("property", propertyDAO.getProperty(id));
        return X;
    }

    @RequestMapping(value = Z, method = RequestMethod.POST)
    public String processUpdateSubmit(@PathVariable(value = "id") String id,
                                      @ModelAttribute("property") Property property, BindingResult bindingResult) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        if (bindingResult.hasErrors())
            return X;
        propertyDAO.updateProperty(property, id);
        return X;
    }

    @RequestMapping(value = Z)
    public String processDelete(@PathVariable(value = "id") String id) {
        propertyDAO.deleteProperty(id);
        return X;
    }
}
