package es.uji.daal.easyrent.view_models;

import es.uji.daal.easyrent.model.BookingProposal;

import java.sql.Date;

/**
 * Created by Alberto on 16/05/2016.
 */
public class BookingForm implements ViewModel<BookingProposal> {
    private Date startDate;
    private Date endDate;
    private int numberOfTenants;

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

    public int getNumberOfTenants() {
        return numberOfTenants;
    }

    public void setNumberOfTenants(int numberOfTenants) {
        this.numberOfTenants = numberOfTenants;
    }


    @Override
    public BookingProposal update(BookingProposal model) {
        model.setStartDate(getStartDate());
        model.setEndDate(getEndDate());
        model.setNumberOfTenants(getNumberOfTenants());
        return model;
    }
}
