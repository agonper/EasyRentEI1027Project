package models.transfer_objects;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum UserRoles {
    OWNER("owner"),
    TENANT("tenant"),
    ADMINISTRATOR("admin");

    private final String serializedRole;

    private UserRoles(String serializedRole) {
        this.serializedRole = serializedRole;
    }

    public String toString() {
        return this.serializedRole;
    }
}
