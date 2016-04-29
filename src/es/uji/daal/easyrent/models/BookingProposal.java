package es.uji.daal.easyrent.models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 27/02/16.
 */
public class BookingProposal extends DomainModel {
    private UUID propertyID;
    private UUID tenantID;
    private Date startDate;
    private Date endDate;
    private ProposalStatus status;
    private String paymentReference;
    private float totalAmount;
    private int numberOfTenants;
    private Date dateOfCreation;
    private Date dateOfAcceptation;

    public UUID getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(UUID propertyID) {
        this.propertyID = propertyID;
    }

    public UUID getTenantID() {
        return tenantID;
    }

    public void setTenantID(UUID tenantID) {
        this.tenantID = tenantID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getNumberOfTenants() {
        return numberOfTenants;
    }

    public void setNumberOfTenants(int numberOfTenants) {
        this.numberOfTenants = numberOfTenants;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfAcceptation() {
        return dateOfAcceptation;
    }

    public void setDateOfAcceptation(Date dateOfAcceptation) {
        this.dateOfAcceptation = dateOfAcceptation;
    }

    /**
     * ======
     * Extra
     * ======
     */

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