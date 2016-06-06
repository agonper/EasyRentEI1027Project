package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
@Entity
@Table(name = "availability_periods")
public class AvailabilityPeriod extends DomainModel {

    @ManyToOne(optional = false)
    private Property property;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    /**
     * ======
     * Methods
     * ======
     */

    protected AvailabilityPeriod() {
    }

    public AvailabilityPeriod(Property property) {
        this.property = property;
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
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
