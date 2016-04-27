package es.uji.daal.easyrent.models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class Photo extends DomainModel {
    private UUID propertyID;
    private UUID userID;
    private String filename;
    private Date uploadDate;

    public UUID getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(UUID propertyID) {
        this.propertyID = propertyID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
