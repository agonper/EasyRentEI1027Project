package es.uji.daal.easyrent.model;

import java.sql.Date;
import java.util.Set;

/**
 * Created by alberto on 17/03/16.
 */
public class User extends DomainModel {
    private String username;
    private String DNI;
    private UserRole role;
    private String password;
    private String name;
    private String surnames;
    private String email;
    private String phoneNumber;
    private String postalAddress;
    private String country;
    private int postCode;
    private Date signUpDate;
    private boolean active;
    private Date deactivatedSince;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public Set<BookingProposal> getBookingProposals() {
        // TODO: Implement
        return null;
    }

    public Set<Property> getProperties() {
        // TODO: Implement
        return null;
    }

    public User activate() {
        // TODO: Implement
        return this;
    }

    public User deactivate() {
        // TODO: Implement
        return this;
    }
}
