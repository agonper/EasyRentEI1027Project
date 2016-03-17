package models.transfer_objects;

import javax.management.InvalidAttributeValueException;
import java.sql.Date;
import java.util.List;

public class Property {

    public int propertyID;
    public String username;
    public String title;
    public String location;
    public int rooms;
    public int capacity;
    public int beds;
    public int bathrooms;
    public int floorSpace;
    public float pricePerDay;
    public PropertyType type;
    public String description;
    public Date creationDate;

    public User getOwner() {
        // TODO: Implement
        return null;
    }

    public List<AvailabilityPeriod> getAvailabilityPeriods() {
        // TODO: Implement
        return null;
    }

    public List<BookingProposal> getBookingProposals() {
        // TODO: Implement
        return null;
    }

    public List<Photo> getPhotos() {
        // TODO: Implement
        return null;
    }

    public List<Service> getServices() {
        // TODO: Implement
        return null;
    }
}
