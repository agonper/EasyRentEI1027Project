package es.uji.daal.easyrent.models;

/**
 * Created by Alberto on 17/03/2016.
 */
public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String serializedRole) {
        super("No role was found for: " + serializedRole);
    }
}
