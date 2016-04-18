package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 27/02/16.
 */
public class Invoice extends Model {
    public int number;
    public UUID proposalID;
    public float vat;
    public String address;
    public Date expeditionDate;

    public BookingProposal getBookingProposal() {
        // TODO: Implement
        return null;
    }
}
