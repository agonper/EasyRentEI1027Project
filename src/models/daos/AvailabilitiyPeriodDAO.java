package models.daos;

import models.common.Store;
import models.transfer_objects.AvailabilityPeriod;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class AvailabilitiyPeriodDAO extends DAO implements Store<AvailabilityPeriod> {
    @Override
    public List<AvailabilityPeriod> findAllRecords() {
        return null;
    }

    @Override
    public AvailabilityPeriod findRecordByID(String id) {
        return null;
    }

    @Override
    public AvailabilityPeriod storeRecord(AvailabilityPeriod record) {
        return null;
    }

    @Override
    public AvailabilityPeriod updateRecord(AvailabilityPeriod record) {
        return null;
    }

    @Override
    public void destroyRecord(AvailabilityPeriod record) {

    }
}
