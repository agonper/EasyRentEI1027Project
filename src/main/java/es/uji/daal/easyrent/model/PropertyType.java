package es.uji.daal.easyrent.model;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum PropertyType {
    FLAT("Flat", "flat", "FLAT"),
    STUDY("Study", "study", "STUDY"),
    PENTHOUSE("Penthouse", "penthouse", "PENTHOUSE"),
    HOUSE("House", "house", "HOUSE"),
    DETACHED_HOUSE("Detached house", "detached house", "DETACHED HOUSE"),
    COTTAGE("Cottage", "cottage", "COTTAGE"),
    CHALET("Chalet", "chalet", "CHALET"),
    APARTMENT("Apartment", "apartment", "APARTMENT");

    private final String name;
    private final String lowerName;
    private final String upperName;

    private PropertyType(String name, String lowerName, String upperName) {
        this.name = name;
        this.lowerName = lowerName;
        this.upperName = upperName;
    }

    public String toString() {
        return this.lowerName;
    }

    public String getName() {
        return this.name;
    }

    public String getLowerName() {
        return this.lowerName;
    }

    public String getUpperName() {
        return this.upperName;
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
            if (suitableType != null) {
                break;
            }
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
