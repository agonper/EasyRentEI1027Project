package models.transfer_objects;

import models.common.Model;

import java.sql.Date;
import java.util.Set;

public class Property extends Model {
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

    public Set<AvailabilityPeriod> getAvailabilityPeriods() {
        // TODO: Implement
        return null;
    }

    public Set<BookingProposal> getBookingProposals() {
        // TODO: Implement
        return null;
    }

    public Set<Photo> getPhotos() {
        // TODO: Implement
        return null;
    }

    public Set<Service> getServices() {
        // TODO: Implement
        return null;
    }
}
