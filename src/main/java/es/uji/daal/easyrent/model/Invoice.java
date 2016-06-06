package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by daniel on 27/02/16.
 */
@Entity
@Table(name = "invoices")
public class Invoice extends DomainModel {

    @Column(columnDefinition = "serial")
    private int number;

    @OneToOne(optional = false)
    private BookingProposal proposal;

    @Column(nullable = false)
    private float vat;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Date expeditionDate;

    /**
     * ======
     * Methods
     * ======
     */

    public Invoice() {
        expeditionDate = new Date();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public BookingProposal getProposal() {
        return proposal;
    }

    public void setProposal(BookingProposal proposal) {
        this.proposal = proposal;
    }
}
