package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
@Entity
@Table(name = "services")
public class Service extends DomainModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String value;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Date creationDate;

    private Date activeSince;

    @Column(nullable = false)
    private int serviceProposals;

    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<Property> properties;

    /**
     * ======
     * Methods
     * ======
     */

    protected Service() {
    }

    public Service(User user) {
        this.user = user;
        creationDate = new Date(new java.util.Date().getTime());
        active = false;
        serviceProposals = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public int getServiceProposals() {
        return serviceProposals;
    }

    public void setServiceProposals(int serviceProposals) {
        this.serviceProposals = serviceProposals;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service activate() {
        active = true;
        activeSince = new Date(new java.util.Date().getTime());
        return this;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void addProperties(Collection<Property> properties) {
        this.properties.addAll(properties);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public void removeProperties(Collection<Property> properties) {
        this.properties.removeAll(properties);
    }
}
