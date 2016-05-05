package es.uji.daal.easyrent.model;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 27/02/16.
 */
public class Invoice extends DomainModel {
    private int number;
    private UUID proposalID;
    private float vat;
    private String address;
    private Date expeditionDate;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UUID getProposalID() {
        return proposalID;
    }

    public void setProposalID(UUID proposalID) {
        this.proposalID = proposalID;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public BookingProposal getBookingProposal() {
        // TODO: Implement
        return null;
    }
}
