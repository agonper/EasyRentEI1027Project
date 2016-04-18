package es.uji.daal.easyrent.models;

import es.uji.daal.easyrent.daos.UserDAO;
import es.uji.daal.easyrent.daos.UserDAO;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Property extends Model {
    public UUID ownerID;
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
    //TODO: La línea de abajo es una propuesta de modificación
    public List<UUID> photosUUIDs;

    public User getOwner() {
        // TODO: Implement
        UserDAO toObtainUser = new UserDAO();
        User ownerObtained = toObtainUser.findRecordByID(ownerID);
        return ownerObtained;
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
