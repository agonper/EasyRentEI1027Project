package models.transfer_objects;

/**
 * Created by Alberto on 17/03/2016.
 */
public enum ProposalStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    public String toString() {
        return this.name().toLowerCase();
    }

    public static ProposalStatus obtainStatusFor(String serializedStatus) throws StatusNotFoundException {
        ProposalStatus status = findSuitableStatus(serializedStatus);
        if (status == null) {
            throw new StatusNotFoundException(serializedStatus);
        } else {
            return status;
        }
    }

    private static ProposalStatus findSuitableStatus(String serializedStatus) {
        ProposalStatus suitableStatus = null;
        for (ProposalStatus status : ProposalStatus.values()) {
            suitableStatus = status.getStatusIfSuitable(serializedStatus);
        }
        return suitableStatus;
    }

    private ProposalStatus getStatusIfSuitable(String serializerStatus) {
        if (isSuitableStatus(serializerStatus)) {
            return this;
        }
        return null;
    }

    private boolean isSuitableStatus(String serializedStatus) {
        return this.toString().equals(serializedStatus);
    }
}