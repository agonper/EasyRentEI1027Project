package es.uji.daal.easyrent.models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 27/02/16.
 */
public class BookingProposal extends Model {
    public UUID propertyID;
    public UUID tenantID;
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