package models.transfer_objects;

import java.sql.Date;
import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class User {
    public String username;
    public String DNI;
    public UserRoles role;
    public String password;
    public String name;
    public String surname;
    public String email;
    public String phoneNumber;
    public String postalAddress;
    public String country;
    public String postCode;
    public Date signUpDate;
    public Boolean active;
    public Date deactivatedSince;

    public List<BookingProposal> getBookingProposals() {
        // TODO: Implement
        return null;
    }

    public List<Property> getProperties() {
        // TODO: Implement
        return null;
    }
}
