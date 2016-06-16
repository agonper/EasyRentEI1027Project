package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.*;

import es.uji.daal.easyrent.repository.*;
import es.uji.daal.easyrent.utils.DateUtils;
import es.uji.daal.easyrent.utils.FileUploader;
import es.uji.daal.easyrent.validators.AddressInfoValidator;
import es.uji.daal.easyrent.validators.BookingValidator;
import es.uji.daal.easyrent.validators.PersonalDataValidator;
import es.uji.daal.easyrent.validators.PropertyValidator;
import es.uji.daal.easyrent.view_models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyRepository repository;

    @Autowired
    private BookingProposalRepository proposalRepository;

    @Autowired
    private FileUploader fileUploader;

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = repository.findOne(UUID.fromString(id));

        if (!loggedUser.equals(property.getOwner())) {
            return "redirect:../show/" + id + ".html";
        }

        model.addAttribute("propertyForm", new PropertyForm().fillUp(property));
        fillEditPage(model, property);
        return "property/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("propertyForm") PropertyForm propertyForm,
                                      BindingResult bindingResult,
                                      @PathVariable("id") String id,
                                      Model model) {
        PropertyValidator validator = new PropertyValidator();
        validator.validate(propertyForm, bindingResult);

        Property property = repository.findOne(UUID.fromString(id));
        fillEditPage(model, property);
        if (bindingResult.hasErrors()) {
            return "property/edit";
        }

        repository.save(propertyForm.update(property));
        return "redirect:" + id + ".html?success=t";
    }

    private void fillEditPage(Model model, Property property) {
        model.addAttribute("property", property);
        model.addAttribute("availabilityForm", new AvailabilityForm());
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID propertyId = UUID.fromString(id);
        if (repository.exists(propertyId)) {
            repository.delete(propertyId);
        }
        return "redirect:../../index.html#owner";
    }

    @ResponseBody
    @RequestMapping(value = "/{propertyId}/upload-photos", method = RequestMethod.POST)
    public String add(@RequestParam(value = "type", defaultValue = "storage") String type,
                      @RequestParam("file") MultipartFile file,
                      HttpSession session, @PathVariable("propertyId") String propertyId) {
        UploadType requestType = UploadType.valueOf(type.toUpperCase());
        if (requestType == UploadType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            if (addProperty != null) {
                Set<String> propertyPhotos = (Set<String>) addProperty.get("propertyPhotos");
                String filename = fileUploader.upload("property-pics", file);
                if (filename != null) {
                    propertyPhotos.add(filename);
                    return filename;
                }
                return "none";
            }
            return "none";
        } else {
            return ""; //TODO: Implement
        }
    }
}
