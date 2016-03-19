package models.transfer_objects;

import models.common.Model;

import java.sql.Date;
import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class User extends Model {
    public String username;
    public String DNI;
    public UserRole role;
    public String password;
    public String name;
    public String surname;
    public String email;
    public String phoneNumber;
    public String postalAddress;
    public String country;
    public int postCode;
    public Date signUpDate;
    public boolean active;
    public Date deactivatedSince;

    public List<BookingProposal> getBookingProposals() {
        // TODO: Implement
        return null;
    }

    public List<Property> getProperties() {
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
