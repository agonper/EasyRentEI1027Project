package es.uji.daal.easyrent.models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class AvailabilityPeriod extends DomainModel {
    private UUID propertyID;
    private Date startDate;
    private Date endDate;

    public UUID getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(UUID propertyID) {
        this.propertyID = propertyID;
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

    /**
     * ======
     * Extra
     * ======
     */

    public Property getProperty() {
        // TODO: Implement
        return null;
    }
}
