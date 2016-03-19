package models.transfer_objects;

import java.sql.Date;

/**
 * Created by daniel on 27/02/16.
 */
public class BookingProposal {
    public String proposalID;
    public String propertyID;
    public String tenantID;
    public Date startDate;
    public Date endDate;
    public ProposalStatus status;
    public String paymentReference;
    public float totalAmount;
    public int numberOfTenants;
    public Date dateOfCreation;
    public Date dateOfAcceptation;

    public Property getProperty() {
        // TODO: Implement
        return null;
    }

    public User getTenant() {
        // TODO: Implement
        return null;
    }

    public BookingProposal accept() {
        // TODO: Implement
        return this;
    }

    public BookingProposal reject() {
        // TODO: Implement
        return this;
    }
}