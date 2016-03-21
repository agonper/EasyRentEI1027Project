package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServices extends Model {
    public UUID propertyID;
    public UUID serviceID;
    public Date offeredSince;
}
