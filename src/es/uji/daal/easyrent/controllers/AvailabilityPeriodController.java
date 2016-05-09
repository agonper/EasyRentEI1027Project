package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.AvailabilityPeriodDAO;
import es.uji.daal.easyrent.daos.PropertyDAO;
import es.uji.daal.easyrent.models.AvailabilityPeriod;
import es.uji.daal.easyrent.models.Property;
import es.uji.daal.easyrent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 3/05/16.
 */
@Controller
@RequestMapping("/property/availabilityPeriod")
public class AvailabilityPeriodController {
    @Autowired
    AvailabilityPeriodDAO availabilityPeriodDAO;

    @Autowired
    PropertyDAO propertyDAO;

    @RequestMapping("/listAll")
    public String listAvailabilityPeriods(Model model) {
        List<AvailabilityPeriod> availabilityPeriods = availabilityPeriodDAO.findAll();
        model.addAttribute("availabilityPeriods", availabilityPeriods);
        return "property/availabilityPeriod/listAll";
    }

    @RequestMapping("/listOwn")
    public String listOwnAvailabilityPeriods(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser != null) {
            UUID userID = loggedUser.getId();
            List<Property> userProperties = propertyDAO.findByUserID(userID);
            List<AvailabilityPeriod> availabilityPeriods = new LinkedList<>();

            //TODO: Mejorar eficiencia en las querys
            for (Property property : userProperties)
                availabilityPeriods.add(availabilityPeriodDAO.getByPropertyID(property.getId()));

            model.addAttribute("ownAvailabilityPeriods", availabilityPeriods);

            return "property/availabilityPeriod/listOwn";
        }
        else
            return "redirect:../../login.html";
    }
}
