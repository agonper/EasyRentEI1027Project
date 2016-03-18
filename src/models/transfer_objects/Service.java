package models.transfer_objects;

import java.sql.Date;

/**
 * Created by alberto on 17/03/16.
 */
public class Service extends Model {
    public String serviceName;
    public String serviceValue;
    public String username;
    public boolean active;
    public Date creationDate;
    public Date activeSince;
    public Date offeredSince;

    public Service activate() {
        // TODO: Implement
        return this;
    }
}
