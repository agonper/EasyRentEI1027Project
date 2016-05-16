package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Property;

import es.uji.daal.easyrent.model.PropertyType;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.PropertyRepository;
import es.uji.daal.easyrent.validators.PropertyValidator;
import es.uji.daal.easyrent.view_models.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    PropertyRepository repository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Property> properties = (List<Property>) repository.findAll();
        model.addAttribute("properties", properties);
        return "property/list";
    }

    @RequestMapping("/listOwnProperties")
    public String listOwnProperties(Model model) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser != null) {
            UUID userID = loggedUser.getId();
            List<Property> userProperties = repository.findByOwner_Id(userID);
            model.addAttribute("userProperties", userProperties);
            return "property/listOwnProperties";
        }
        return "redirect:../login.html";
    }

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        Property property = repository.findOne(UUID.fromString(id));
        model.addAttribute("property", property);
        return "property/show";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser != null) {
            model.addAttribute("propertyTypes", PropertyType.values());
            model.addAttribute("property", new Property(loggedUser));
            return "property/add";
        }
        return "redirect:../login.html";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("property") Property property,
                                   BindingResult bindingResult) {

        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors() || loggedUser == null)
            return "property/add";

        property.setCreationDate(new Date(System.currentTimeMillis()));
        property.setOwner(loggedUser);
        repository.save(property);
        return "redirect:../user/owner/"+ loggedUser.getId() +".html";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("property", repository.findOne(UUID.fromString(id)));
        return "property/update";
    }

    //TODO: Revisar validez del ID
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("property") Property property, BindingResult bindingResult) {
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, bindingResult);

        if (bindingResult.hasErrors())
            return "property/update";

        repository.save(property);
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {
        UUID propertyId = UUID.fromString(id);
        if (repository.exists(propertyId)) {
            repository.delete(propertyId);
        }
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/booking-proposal/{id}")
    public String bookingProposal(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = repository.findOne(UUID.fromString(id));
        if (property.getOwner().equals(loggedUser)) {
            return "redirect:../show/"+id+".html";
        }
        model.addAttribute("property", property);
        model.addAttribute("bookingForm", new BookingForm());
        return "bookingProposal/add";
    }
}
