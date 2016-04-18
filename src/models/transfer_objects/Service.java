package models.transfer_objects;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class Service extends Model {
    public String serviceName;
    public String serviceValue;
    public UUID user_id;
    public boolean active;
    public Date creationDate;
    public Date activeSince;
    public int serviceProposals;

    public Service activate() {
        //TODO: Validar implementación
        active = true;
        return this;
    }
}
