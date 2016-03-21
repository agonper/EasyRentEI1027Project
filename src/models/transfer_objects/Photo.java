package models.transfer_objects;

import java.sql.Date;

/**
 * Created by alberto on 17/03/16.
 */
public class Photo extends Model {
    //TODO: Eliminado propertyID porque las fotos pueden ser también de usuarios
    public String filename;
    public Date uploadDate;
}
