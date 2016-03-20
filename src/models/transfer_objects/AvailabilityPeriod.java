package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class AvailabilityPeriod extends Model {
    public UUID propertyID;
    public Date startDate;
    public Date endDate;


    public Property getProperty() {
        // TODO: Implement
        return null;
    }
}
