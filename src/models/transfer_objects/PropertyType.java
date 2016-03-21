package models.transfer_objects;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum PropertyType {
    PENDING,
    ACCEPTED,
    REJECTED;

    public String toString() {
        return this.name().toLowerCase();
    }

    public static PropertyType obtainTypeFor(String serializedType) throws TypeNotFoundException {
        PropertyType type = findSuitableType(serializedType);
        if (type == null) {
            throw new TypeNotFoundException(serializedType);
        } else {
            return type;
        }
    }

    private static PropertyType findSuitableType(String serializedType) {
        PropertyType suitableType = null;
        for (PropertyType type : PropertyType.values()) {
            suitableType = type.getTypeIfSuitable(serializedType);
        }
        return suitableType;
    }

    private PropertyType getTypeIfSuitable(String serializedType) {
        if (isSuitableType(serializedType)) {
            return this;
        }
        return null;
    }

    private boolean isSuitableType(String serializedType) {
        return this.toString().equals(serializedType);
    }
}
