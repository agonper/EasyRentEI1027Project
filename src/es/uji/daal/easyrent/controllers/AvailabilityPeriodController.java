package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.AvailabilityPeriodDAO;
import es.uji.daal.easyrent.daos.PropertyDAO;
import es.uji.daal.easyrent.models.AvailabilityPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by daniel on 3/05/16.
 */
@Controller
@RequestMapping("/property/availabilityPeriod")
public class AvailabilityPeriodController {
    @Autowired
    AvailabilityPeriodDAO availabilityPeriodDAO;

    @RequestMapping("/listAll")
    public String listAvailabilityPeriods(Model model) {
        List<AvailabilityPeriod> availabilityPeriods = availabilityPeriodDAO.findAll();
        model.addAttribute("availabilityPeriods", availabilityPeriods);
        return "property/availabilityPeriod/listAll";
    }
}
