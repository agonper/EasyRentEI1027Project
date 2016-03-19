package models.transfer_objects;

import java.sql.Date;
import java.util.Set;

/**
 * Created by alberto on 17/03/16.
 */
public class User extends Model {
    public String username;
    public String DNI;
    public UserRole role;
    public String password;
    public String name;
    public String surnames;
    public String email;
    public String phoneNumber;
    public String postalAddress;
    public String country;
    public int postCode;
    public Date signUpDate;
    public boolean active;
    public Date deactivatedSince;

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
