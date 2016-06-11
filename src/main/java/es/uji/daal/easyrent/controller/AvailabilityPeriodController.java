package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.AvailabilityPeriod;
import es.uji.daal.easyrent.repository.AvailabilityPeriodRepository;
import es.uji.daal.easyrent.view_models.AvailabilityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 3/05/16.
 */
@Controller
@RequestMapping("/property/availability-period") //FIXME me he cargado algo, creo que no lo usábamos ya
public class AvailabilityPeriodController {
    @Autowired
    AvailabilityPeriodRepository repository;

    @RequestMapping("/listAll")
    public String listAvailabilityPeriods(Model model) {
        List<AvailabilityPeriod> availabilityPeriods = (List<AvailabilityPeriod>) repository.findAll();
        model.addAttribute("availabilityPeriods", availabilityPeriods);
        return "property/availabilityPeriod/listAll";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "type", defaultValue = "storage") String type,
                      @ModelAttribute AvailabilityForm availabilityForm,
                      BindingResult bindingResult,
                      HttpSession session) {
        RequestType requestType = RequestType.valueOf(type.toUpperCase());
        if (requestType == RequestType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            List<AvailabilityForm> availabilities = (List<AvailabilityForm>) addProperty.get("availabilityPeriods");
            if (!bindingResult.hasErrors()) {
                availabilities.add(availabilityForm);
            }
            return "redirect:../add.html?step=3";
        } else {
            return ""; //FIXME: Implement
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@RequestParam(value = "type", defaultValue = "storage") String type,
                         @PathVariable("id") String id,
                         @ModelAttribute AvailabilityForm availabilityForm,
                         BindingResult bindingResult,
                         HttpSession session) {
        RequestType requestType = RequestType.valueOf(type.toUpperCase());
        if (requestType == RequestType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            List<AvailabilityForm> availabilities = (List<AvailabilityForm>) addProperty.get("availabilityPeriods");
            if (!bindingResult.hasErrors()) {
                int index = Integer.parseInt(id);
                availabilities.set(index, availabilityForm);
            }
            return "redirect:../../add.html?step=3";
        } else {
            return ""; //FIXME: Implement
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@RequestParam(value = "type", defaultValue = "storage") String type,
                         @PathVariable("id") String id,
                         HttpSession session) {
        RequestType requestType = RequestType.valueOf(type.toUpperCase());
        if (requestType == RequestType.SESSION) {
            Map<String, Object> addProperty = (Map<String, Object>) session.getAttribute("addPropertyMap");
            List<AvailabilityForm> availabilities = (List<AvailabilityForm>) addProperty.get("availabilityPeriods");
            int index = Integer.parseInt(id);
            availabilities.remove(index);
            return "redirect:../../add.html?step=3";
        } else {
            return ""; //FIXME: Implement
        }
    }

    private enum RequestType {
        SESSION, STORAGE
    }
}
