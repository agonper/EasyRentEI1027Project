package es.uji.daal.easyrent.controllers;

import es.uji.daal.easyrent.daos.AvailabilityPeriodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daniel on 23/04/16.
 */

@Controller
@RequestMapping()
public class AvailabilityPeriodController {

    private AvailabilityPeriodDAO availabilityPeriodDAO;

    @Autowired
    public void setAvailabilitiyPeriodDAO(AvailabilityPeriodDAO availabilityPeriodDAO) {
        this.availabilityPeriodDAO = availabilityPeriodDAO;
    }


}
