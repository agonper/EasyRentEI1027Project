package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by daniel on 27/02/16.
 */
public class Invoice extends Model {
    //TODO: Modificado invoiceNumber por number, invoice es redundante
    public int number;
    public UUID proposalID;
    //TODO: Modificado actualVAT por vat, ya que el actual puede ser confuso, se entiende que es el que hay registrado en la factura
    //TODO: Modificado de float a entero por consistencia con la base de datos
    public int vat;
    public String address;
    public Date expeditionDate;

    public BookingProposal getBookingProposal() {
        // TODO: Implement
        return null;
    }
}
