package models.daos;

import models.common.Store;
import models.transfer_objects.Service;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class ServiceDAO implements Store<Service> {
    @Override
    public List<Service> findAllRecords() {
        return null;
    }

    @Override
    public Service findRecordByID(String id) {
        return null;
    }

    @Override
    public Service storeRecord(Service record) {
        return null;
    }

    @Override
    public Service updateRecord(Service record) {
        return null;
    }

    @Override
    public void destroyRecord(Service record) {

    }
}
