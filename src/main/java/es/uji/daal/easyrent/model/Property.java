package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "properties")
public class Property extends DomainModel {

    @ManyToOne(optional = false)
    private User owner;

    @Field(analyzer = @Analyzer(impl = SpanishAnalyzer.class))
    @Column(nullable = false)
    private String title;

    @Field(analyzer = @Analyzer(impl = SpanishAnalyzer.class))
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

    @Field(analyzer = @Analyzer(impl = SpanishAnalyzer.class))
    private String description;

    @Column(nullable = false)
    private Date creationDate;

    @OrderBy("startDate asc ")
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
        creationDate = new Date();
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

    public AvailabilityPeriod createAvailabilityPeriod() {
        return new AvailabilityPeriod(this);
    }

    public Set<BookingProposal> getBookingProposals() {
        return bookingProposals;
    }

    public BookingProposal createBookingProposal(User user) {
        return new BookingProposal(this, user);
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Photo createPhoto(String filename) {
        return new Photo(this, filename);
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(HashSet<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        if (getServices() == null) {
            setServices(new HashSet<>());
        }
        getServices().add(service);
    }

    public void addServices(Collection<Service> services) {
        if (getServices() == null) {
            setServices(new HashSet<>());
        }
        getServices().addAll(services);
    }

    @PreRemove
    void preRemove() {
        for (BookingProposal proposal : bookingProposals) {
            proposal.setProperty(null);
        }
    }
}
