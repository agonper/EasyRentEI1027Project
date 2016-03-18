package models.transfer_objects;

import models.common.Model;

import java.sql.Date;

/**
 * Created by alberto on 17/03/16.
 */
public class AvailabilityPeriod extends Model {
    public String propertyID;
    public Date startDate;
    public Date endDate;

    public Property getProperty() {
        // TODO: Implement
        return null;
    }
}
