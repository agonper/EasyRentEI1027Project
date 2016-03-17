package models.transfer_objects;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum UserRole {
    OWNER,
    TENANT,
    ADMINISTRATOR;

    public String toString() {
        return this.name().toLowerCase();
    }

    public static UserRole obtainRole(String serializedRole) throws RoleNotFoundException {
        UserRole role = findSuitableRole(serializedRole);
        if (role == null) {
            throw new RoleNotFoundException(serializedRole);
        } else {
            return role;
        }
    }

    private static UserRole findSuitableRole(String serializedRole) {
        UserRole suitableRole = null;
        for (UserRole role : UserRole.values()) {
            suitableRole = role.getRoleIfSuitable(serializedRole);
        }
        return suitableRole;
    }

    private UserRole getRoleIfSuitable(String serializerRole) {
        if (isSuitableRole(serializerRole)) {
            return this;
        }
        return null;
    }

    private boolean isSuitableRole(String serializedRole) {
        return this.toString().equals(serializedRole);
    }
}
