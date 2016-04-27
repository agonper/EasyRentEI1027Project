package es.uji.daal.easyrent.models;

import es.uji.daal.easyrent.daos.UserDAO;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Property extends Model {
    private UUID ownerID;
    private String title;
    private String location;
    private int rooms;
    private int capacity;
    private int beds;
    private int bathrooms;
    private int floorSpace;
    private float pricePerDay;
    private PropertyType type;
    private String description;
    private Date creationDate;
    //TODO: La línea de abajo es una propuesta de modificación
    /*
    Alberto: Creo que no sería conveniente, quizá lo mejor es poner dos métodos en el DAO de Photos, obtenerlas por
                Usuario o Propiedad
     */
    public List<UUID> photosUUIDs;

    public UUID getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(int floorSpace) {
        this.floorSpace = floorSpace;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public User getOwner() {
        // TODO: Implement
        UserDAO toObtainUser = new UserDAO();
        User ownerObtained = toObtainUser.findOneByID(ownerID);
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
