package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class Photo extends Model {
    public UUID propertyID;
    public UUID userID;
    public String filename;
    public Date uploadDate;
}
