package models.daos;

import models.common.Store;
import models.transfer_objects.Service;

import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class ServiceDAO extends DAO implements Store<Service> {
    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    @Override
    public Set<Service> findAllRecords() {
        return null;
    }

    @Override
    public Service findRecordByID(UUID id) {
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
