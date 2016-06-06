package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

/**
 * Created by alberto on 17/03/16.
 */

@Entity
@Table(name = "users")
public class User extends DomainModel {
    @Column(unique = true, nullable = false)
    public String username;

    @Column(unique = true)
    public String Dni;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRole role;

    @Column(nullable = false)
    public String password;

    public String name;

    public String surnames;

    @Column(unique = true, nullable = false)
    public String email;

    public String phoneNumber;

    public String postalAddress;

    public String country;

    public int postCode;

    @Column(nullable = false)
    public Date signUpDate;

    @Column(nullable = false)
    public boolean active;

    public Date deactivatedSince;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @OrderBy("creationDate desc ")
    private Set<Property> properties;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private Set<BookingProposal> bookingProposals;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Photo photo;

    public User() {
        role = UserRole.TENANT;
        signUpDate = new Date(new java.util.Date().getTime());
        active = true; //TODO:For testing
    }

    /**
     * ======
     * Methods
     * ======
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String DNI) {
        this.Dni = DNI;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDeactivatedSince() {
        return deactivatedSince;
    }

    public void setDeactivatedSince(Date deactivatedSince) {
        this.deactivatedSince = deactivatedSince;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public Set<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
        property.setOwner(this);
    }

    public void addProperties(Collection<Property> properties) {
        this.properties.addAll(properties);
        for (Property property : properties) {
            property.setOwner(this);
        }
    }

    public void removeProperty(Property property) {
        properties.remove(property);
        property.setOwner(null);
    }

    public void removeProperties(Collection<Property> properties) {
        this.properties.removeAll(properties);
        for (Property property : properties) {
            property.setOwner(null);
        }
    }

    public Set<BookingProposal> getBookingProposals() {
        return bookingProposals;
    }

    public void addBookingProposal(BookingProposal proposal) {
        bookingProposals.add(proposal);
        proposal.setTenant(this);
    }

    public void addBookingProposals(Collection<BookingProposal> proposals) {
        this.bookingProposals.addAll(proposals);
        for (BookingProposal proposal : proposals) {
            proposal.setTenant(this);
        }
    }

    public void removeBookingProposal(BookingProposal proposal) {
        bookingProposals.remove(proposal);
        proposal.setTenant(null);
    }

    public void removeBookingProposals(Collection<BookingProposal> proposals) {
        this.bookingProposals.removeAll(proposals);
        for (BookingProposal proposal : proposals) {
            proposal.setTenant(null);
        }
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        photo.setUser(this);
    }

    public User activate() {
        this.active = true;
        this.deactivatedSince = null;
        return this;
    }

    public User deactivate() {
        this.active = false;
        this.deactivatedSince = new Date(new java.util.Date().getTime());
        return this;
    }

    @PreRemove
    void preRemove() {
        if (!getProperties().isEmpty()) {
            throw new IllegalStateException("An owner needs to be without properties before removing it!");
        }
        for (BookingProposal proposal : bookingProposals) {
            proposal.setTenant(null);
        }
    }
}
