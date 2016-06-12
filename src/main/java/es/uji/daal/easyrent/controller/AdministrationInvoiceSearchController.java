package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Invoice;
import es.uji.daal.easyrent.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel on 12/06/16.
 */

@Controller
@RequestMapping("administration/invoices")
public class AdministrationInvoiceSearchController {

    @Autowired
    InvoiceRepository repository;

    @RequestMapping("/searchFor")
    public String searchFor(@RequestParam String searchedFor, @RequestParam String selectedInvoiceAttribute, Model model) {

        List<Invoice> searchResult = new LinkedList<>();

        switch (selectedInvoiceAttribute) {
            case "ID":
                //TODO: Completar
                break;

            case "number":
                Integer number;
                try {
                    number = Integer.parseInt(searchedFor);
                }
                catch (NumberFormatException e) {
                    number = -1;
                }

                searchResult = repository.findByNumber(number);
                break;

            case "bookingID":
                //TODO: Completar
                break;

            case "vat":
                Float vat;
                try {
                    vat = Float.parseFloat(searchedFor);
                }
                catch (NumberFormatException e) {
                    vat = -1f;
                }

                searchResult = repository.findByVAT(vat);
                break;

            case "address":
                searchResult = repository.findByAddressContainedInSearchedAddress(searchedFor);
                break;

            case "expeditionDate":
                Date expeditionDate;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    expeditionDate = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    expeditionDate = null;
                }
                searchResult = repository.findByExpeditionDate(expeditionDate);
                break;

            case "totalAmount":
                //TODO: Concretar estado en la BBDD
                break;
        }

        model.addAttribute("invoices", searchResult);

        return "administration/invoices";
    }
}
