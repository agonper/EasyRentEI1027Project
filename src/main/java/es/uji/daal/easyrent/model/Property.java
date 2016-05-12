package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "properties")
public class Property extends DomainModel {

    @ManyToOne(optional = false)
    private User owner;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int rooms;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int beds;

    @Column(nullable = false)
    private int bathrooms;

    @Column(nullable = false)
    private int floorSpace;

    @Column(nullable = false)
    private float pricePerDay;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    private String description;

    @Column(nullable = false)
    private Date creationDate;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AvailabilityPeriod> availabilityPeriods;

    @OneToMany(mappedBy = "property")
    private Set<BookingProposal> bookingProposals;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Photo> photos;

    @ManyToMany
    @JoinTable(name = "property_services",
        joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<Service> services;

    /**
     * ======
     * Methods
     * ======
     */

    protected Property() {
    }

    public Property(User owner) {
        this.owner = owner;
        creationDate = new Date(new java.util.Date().getTime());
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
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<AvailabilityPeriod> getAvailabilityPeriods() {
        return availabilityPeriods;
    }

    public void addAvailiabilityPerdiod(AvailabilityPeriod period) {
        availabilityPeriods.add(period);
        period.setProperty(this);
    }

    public void addAvailiabilityPerdiods(Collection<AvailabilityPeriod> periods) {
        availabilityPeriods.addAll(periods);
        for (AvailabilityPeriod period : periods) {
            period.setProperty(this);
        }
    }

    public void removeAvailabilityPeriod(AvailabilityPeriod period) {
        availabilityPeriods.remove(period);
        period.setProperty(null);
    }

    public void removeAvailabilityPeriods(Collection<AvailabilityPeriod> periods) {
        availabilityPeriods.removeAll(periods);
        for (AvailabilityPeriod period : periods) {
            period.setProperty(null);
        }
    }

    public Set<BookingProposal> getBookingProposals() {
        return bookingProposals;
    }

    public void addBookingProposal(BookingProposal proposal) {
        bookingProposals.add(proposal);
        proposal.setProperty(this);
    }

    public void addBookingProposals(Collection<BookingProposal> proposals) {
        bookingProposals.addAll(proposals);
        for (BookingProposal proposal: proposals) {
            proposal.setProperty(this);
        }
    }

    public void removeBookingProposal(BookingProposal proposal) {
        bookingProposals.remove(proposal);
        proposal.setProperty(null);
    }

    public void removeBookingProposals(Collection<BookingProposal> proposals) {
        bookingProposals.removeAll(proposals);
        for (BookingProposal proposal : proposals) {
            proposal.setProperty(null);
        }
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setProperty(this);
    }

    public void addPhotos(Collection<Photo> photos) {
        this.photos.addAll(photos);
        for (Photo photo : photos) {
            photo.setProperty(this);
        }
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setProperty(null);
    }

    public void removePhotos(Collection<Photo> photos) {
        this.photos.removeAll(photos);
        for (Photo photo : photos) {
            photo.setProperty(null);
        }
    }

    public Set<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
        service.addProperty(this);
    }

    public void addServices(Collection<Service> services) {
        this.services.addAll(services);
        for (Service service : services) {
            service.addProperty(this);
        }
    }

    public void removeService(Service service) {
        services.remove(service);
        service.removeProperty(this);
    }

    public void removeServices(Collection<Service> services) {
        this.services.removeAll(services);
        for (Service service : services) {
            service.removeProperty(this);
        }
    }

    @PreRemove
    void preRemove() {
        for (BookingProposal proposal : bookingProposals) {
            proposal.setProperty(null);
        }
    }
}
