package models.transfer_objects;

import java.sql.Date;

/**
 * Created by daniel on 27/02/16.
 */
public class Invoice extends Model {
    public int invoiceNumber;
    public String proposalID;
    public float actualVAT;
    public String address;
    public Date expeditionDate;

    public BookingProposal getBookingProposal() {
        // TODO: Implement
        return null;
    }
}
