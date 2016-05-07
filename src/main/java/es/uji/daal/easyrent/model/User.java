package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by alberto on 17/03/16.
 */

@Entity
@Table(name = "users")
public class User extends DomainModel {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String Dni;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String password;

    private String name;

    private String surnames;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String postalAddress;

    private String country;

    private int postCode;

    @Column(nullable = false)
    private Date signUpDate;

    @Column(nullable = false)
    private boolean active;

    private Date deactivatedSince;

    @OneToMany(mappedBy = "owner")
    private Set<Property> properties;

    @OneToMany(mappedBy = "tenant")
    private Set<BookingProposal> bookingProposals;

    @OneToOne(mappedBy = "user")
    private Photo photo;

    public User() {
        role = UserRole.TENANT;
        signUpDate = new Date(new java.util.Date().getTime());
        active = false;
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

    public boolean getActive() {
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

    public Set<BookingProposal> getBookingProposals() {
        return bookingProposals;
    }

    public Photo getPhoto() {
        return photo;
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
}
