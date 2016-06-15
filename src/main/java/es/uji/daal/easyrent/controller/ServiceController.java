package es.uji.daal.easyrent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.uji.daal.easyrent.model.Service;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.ServiceRepository;
import es.uji.daal.easyrent.utils.Strings;
import es.uji.daal.easyrent.validators.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by daniel on 11/05/16.
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceRepository repository;

    @ResponseBody
    @RequestMapping(value = "/property/{propertyId}/list")
    public String processAddSubmit(@RequestParam(value = "type", defaultValue = "storage") String type,
                                   @PathVariable("propertyId") String propertyId,
                                   HttpSession session) {
        UploadType uploadType = UploadType.valueOf(type.toUpperCase());
        ObjectMapper om = new ObjectMapper();

        if (uploadType == UploadType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            if (addProperty == null) {
                return "ERROR";
            }
            List<Service> services = (List<Service>) addProperty.get("services");
            try {
                return om.writeValueAsString(services);
            } catch (JsonProcessingException e) {
                return "[]";
            }
        } else {
            //TODO: Implement
        }

        return "[]";
    }

    @ResponseBody
    @RequestMapping(value = "/property/{propertyId}/add", method = RequestMethod.POST)
    public String processAddSubmit(@RequestParam("name") String name,
                                   @RequestParam(value = "type", defaultValue = "storage") String type,
                                   @PathVariable("propertyId") String propertyId,
                                   HttpSession session) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String value = Strings.underscore(name);
        Service service = repository.findByValue(value);
        UploadType uploadType = UploadType.valueOf(type.toUpperCase());

        if (uploadType == UploadType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            if (addProperty == null) {
                return "ERROR";
            }
            List<Service> services = (List<Service>) addProperty.get("services");
            if (service == null) {
                service = new Service(loggedUser);
                service.setName(name);
                service.setValue(value);
            }
            for (Service s : services) {
                if (service.getValue().equals(s.getValue())) {
                    return "ERROR";
                }
            }
            services.add(service);
        } else {
            //TODO: Implement
        }

        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/property/{propertyId}/remove/{id}", method = RequestMethod.POST)
    public String processRemoveSubmit(@RequestParam(value = "type", defaultValue = "storage") String type,
                                      @PathVariable("propertyId") String propertyId, @PathVariable("id") String id,
                                      HttpSession session) {
        UploadType uploadType = UploadType.valueOf(type.toUpperCase());

        if (uploadType == UploadType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            if (addProperty == null) {
                return "ERROR";
            }
            List<Service> services = (List<Service>) addProperty.get("services");
            int index = Integer.parseInt(id);
            services.remove(index);
        } else {
            //TODO: Implement
        }

        return "OK";
    }

    @RequestMapping(value = "/changeState/{id}")
    public String processChangeState(@ModelAttribute("service") Service service, @PathVariable(value = "id") String id) {
        Service changedService;
        UUID serviceID = UUID.fromString(id);

        if (repository.exists(serviceID)) {
            changedService = new Service(repository.findOne(serviceID));

            if (!changedService.getActive()) {
                changedService.setActive(true);
                if (changedService.getActiveSince() == null)
                    changedService.setActiveSince(new Date(System.currentTimeMillis()));
            }
            else
                changedService.setActive(false);

            repository.save(changedService);
        }
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("service", repository.findOne(UUID.fromString(id)));
        return "name/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String processUpdate(@ModelAttribute("service") Service service, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        ServiceValidator validator = new ServiceValidator();
        validator.validate(service, bindingResult);

        if (bindingResult.hasErrors())
            return "name/update";

        Service changedService = new Service(repository.findOne(UUID.fromString(id)));
        changedService.setActive(service.getActive());
        changedService.setName(service.getName());
        changedService.setValue(service.getValue());

        repository.save(changedService);
        return "redirect:../list.html";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable(value = "id") String id) {
        UUID serviceID = UUID.fromString(id);
        if (repository.exists(serviceID)) {
            repository.delete(serviceID);
        }
        return "redirect:../list.html";
    }
}
