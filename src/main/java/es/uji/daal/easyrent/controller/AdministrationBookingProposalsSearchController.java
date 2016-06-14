package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.repository.BookingProposalRepository;
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
@RequestMapping("/administration/booking_proposals")
public class AdministrationBookingProposalsSearchController {

    @Autowired
    BookingProposalRepository repository;

    @RequestMapping("/searchFor")
    public String searchFor(@RequestParam String searchedFor, @RequestParam String selectedBookingProposalsAttribute, Model model) {

        List<BookingProposal> searchResult = new LinkedList<>();

        switch (selectedBookingProposalsAttribute) {
            case "propertyTitle":
                searchResult = repository.findByPropertyTitleContainedInSearchedPropertyTitle(searchedFor);
                break;

            case "tenantUsername":
                searchResult = repository.findByTenantUsernameContainedInSearchedTenantUsername(searchedFor);
                break;

            case "startDate":
                Date startDate;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    startDate = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    startDate = null;
                }
                searchResult = repository.findByStartDate(startDate);
                break;

            case "endDate":
                Date endDate;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    endDate = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    endDate = null;
                }
                searchResult = repository.findByEndDate(endDate);
                break;

            case "status":
                searchResult = repository.findByStatus(searchedFor);
                break;

            case "paymentReference":
                searchResult = repository.findByPaymentReferenceContainedInSearchedPaymentReference(searchedFor);
                break;

            case "totalAmount":
                Float totalAmount;
                try {
                    totalAmount = Float.parseFloat(searchedFor);
                }
                catch (NumberFormatException e) {
                    totalAmount = -1f;
                }
                searchResult = repository.findByTotalAmount(totalAmount);
                break;

            case "numberOfTenants":
                Integer numberOfTenants;
                try {
                    numberOfTenants = Integer.parseInt(searchedFor);
                }
                catch(NumberFormatException e) {
                    numberOfTenants = -1;
                }
                searchResult = repository.findByNumberOfTenants(numberOfTenants);
                break;

            case "dateOfCreation":
                Date dateOfCreation;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateOfCreation = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    dateOfCreation = null;
                }
                searchResult = repository.findByDateOfCreation(dateOfCreation);
                break;

            case "dateOfAcceptance":
                Date dateOfAcceptance;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateOfAcceptance = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    dateOfAcceptance = null;
                }
                searchResult = repository.findByDateOfUpdate(dateOfAcceptance);
                break;

            case "invoiceNumber":
                Integer number;
                try {
                    number = Integer.parseInt(searchedFor);
                }
                catch (NumberFormatException e) {
                    number = -1;
                }
                searchResult = repository.findByInvoiceNumber(number);
                break;
        }

        model.addAttribute("bookingProposals", searchResult);

        return "administration/booking_proposals";
    }
}