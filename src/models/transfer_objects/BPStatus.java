package models.transfer_objects;

import java.security.PublicKey;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum BPStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    public String toString() {
        return this.name().toLowerCase();
    }

    public static BPStatus obtainStatus(String serializedStatus) throws StatusNotFoundException {
        BPStatus status = findSuitableStatus(serializedStatus);
        if (status == null) {
            throw new StatusNotFoundException(serializedStatus);
        } else {
            return status;
        }
    }

    private static BPStatus findSuitableStatus(String serializedStatus) {
        BPStatus suitableStatus = null;
        for (BPStatus status : BPStatus.values()) {
            suitableStatus = status.getStatusIfSuitable(serializedStatus);
        }
        return suitableStatus;
    }

    private BPStatus getStatusIfSuitable(String serializerStatus) {
        if (isSuitableStatus(serializerStatus)) {
            return this;
        }
        return null;
    }

    private boolean isSuitableStatus(String serializedStatus) {
        return this.toString().equals(serializedStatus);
    }
}
