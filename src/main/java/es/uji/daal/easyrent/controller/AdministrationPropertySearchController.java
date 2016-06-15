package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Property;
import es.uji.daal.easyrent.repository.PropertyRepository;
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
@RequestMapping("/administration/properties")
public class AdministrationPropertySearchController {

    @Autowired
    PropertyRepository repository;

    @RequestMapping("/searchFor")
    public String searchFor(@RequestParam String searchedFor, @RequestParam String selectedPropertyAttribute, Model model) {

        List<Property> searchResult = new LinkedList<>();

        switch (selectedPropertyAttribute) {

            case "owner":
                searchResult = repository.findByOwnerContainedInSearchedOwner(searchedFor);
                break;

            case "title":
                searchResult = repository.findByTitleContainedInSearchedTitle(searchedFor);
                break;

            case "location":
                searchResult = repository.findByLocationContainedInSearchedLocation(searchedFor);
                break;

            case "rooms":
                Integer numberOfRooms;
                try {
                    numberOfRooms = Integer.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    numberOfRooms = -1;
                }
                searchResult = repository.findByNumberOfRooms(numberOfRooms);
                break;

            case "capacity":
                Integer capacity;
                try {
                    capacity = Integer.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    capacity = -1;
                }
                searchResult = repository.findByCapacityNumber(capacity);
                break;

            case "beds":
                Integer beds;
                try {
                    beds = Integer.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    beds = -1;
                }
                searchResult = repository.findByNumberOfBeds(beds);
                break;

            case "bathrooms":
                Integer bathrooms;
                try {
                    bathrooms = Integer.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    bathrooms = -1;
                }
                searchResult = repository.findByNumberOfBathrooms(bathrooms);
                break;

            case "floorSpace":
                Integer floorSpace;
                try {
                    floorSpace = Integer.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    floorSpace = -1;
                }
                searchResult = repository.findByFloorSpace(floorSpace);
                break;

            case "pricePerDay":
                Float pricePerDay;
                try {
                    pricePerDay = Float.valueOf(searchedFor);
                }
                catch (NumberFormatException e) {
                    pricePerDay = -1f;
                }
                searchResult = repository.findByPricePerDay(pricePerDay);
                break;

            case "creationDate":
                Date creationDate;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    creationDate = formatter.parse(searchedFor);
                }
                catch (ParseException e) {
                    creationDate = null;
                }
                searchResult = repository.findByCreationDate(creationDate);
                break;

            case "type":
                searchResult = repository.findByTypeContainedInSearchedType(searchedFor);
                break;

            case "description":
                searchResult = repository.findByDescriptionContainedInSearchedDescription(searchedFor);
                break;

        }

        model.addAttribute("properties", searchResult);

        return "administration/properties";
    }
}
