package es.uji.daal.easyrent.models;

import java.util.UUID;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServices extends Model {
    private UUID propertyID;
    private UUID serviceID;

    public UUID getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(UUID propertyID) {
        this.propertyID = propertyID;
    }

    public UUID getServiceID() {
        return serviceID;
    }

    public void setServiceID(UUID serviceID) {
        this.serviceID = serviceID;
    }
}
