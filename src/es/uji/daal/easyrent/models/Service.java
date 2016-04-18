package es.uji.daal.easyrent.models;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class Service extends Model {
    private String name;
    private String value;
    private UUID userId;
    private boolean active;
    private Date creationDate;
    private Date activeSince;
    private int serviceProposals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID user_id) {
        this.userId = user_id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public int getServiceProposals() {
        return serviceProposals;
    }

    public void setServiceProposals(int serviceProposals) {
        this.serviceProposals = serviceProposals;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public Service activate() {
        //TODO: Validar implementación
        active = true;
        return this;
    }
}
