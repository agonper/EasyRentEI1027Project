package es.uji.daal.easyrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by daniel on 15/05/16.
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @RequestMapping("/{id}/access")
    public String access() {
        //TODO: Terminate

        return "invoice/access";
    }
}
