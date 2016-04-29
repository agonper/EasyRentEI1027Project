package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.AvailabilityPeriodDAO;
import es.uji.daal.easyrent.daos.DAO;

import es.uji.daal.easyrent.models.AvailabilityPeriod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping("/availabilityPeriod")
public class AvailabilityPeriodController {

    private AvailabilityPeriodDAO availabilityPeriodDAO;

    //TODO: Revisar si la conversión es necesaria
    @Autowired
    public void setAvailabilitiyPeriodDAO(DAO availabilityPeriodDAO) {
        this.availabilityPeriodDAO = (AvailabilityPeriodDAO)availabilityPeriodDAO;
    }

    //TODO: Comprobar cual es válido
    @RequestMapping("{idProperty}/add")
    //@RequestMapping("/add")
    public String addAvailabilityPeriod(Model model, @PathVariable(value = "idProperty") String id) {
        model.addAttribute("availabilityPeriod", new AvailabilityPeriod());
        return "availabilityPeriod/add";
    }


}
