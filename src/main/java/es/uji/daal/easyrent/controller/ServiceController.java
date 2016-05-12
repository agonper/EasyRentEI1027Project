package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Service;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.ServiceRepository;
import es.uji.daal.easyrent.validators.ServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 11/05/16.
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceRepository repository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Service> services = (List<Service>)repository.findAll();
        model.addAttribute("services", services);
        return "service/list";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        //TODO: Revisar la necesidad de comprobar el usuario y redirigir al login con la nueva implementación
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("service", new Service(loggedUser));
        return "service/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("service") Service service, BindingResult bindingResult) {
        ServiceValidator validator = new ServiceValidator();
        validator.validate(service, bindingResult);
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors() || loggedUser == null)
            return "service/add";

        service.setUser(loggedUser);
        service.setCreationDate(new Date(System.currentTimeMillis()));

        if (service.getActive())
            service.setActiveSince(new Date(System.currentTimeMillis()));

        service.setServiceProposals(1);
        repository.save(service);

        return "redirect:list.html";
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
        return "service/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String processUpdate(@ModelAttribute("service") Service service, BindingResult bindingResult, @PathVariable(value = "id") String id) {
        ServiceValidator validator = new ServiceValidator();
        validator.validate(service, bindingResult);

        if (bindingResult.hasErrors())
            return "service/update";

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
