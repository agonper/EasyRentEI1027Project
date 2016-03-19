package models.daos;

import models.common.Store;
import models.transfer_objects.Property;

import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PropertyDAO extends DAO implements Store<Property> {
    public PropertyDAO() {
        super(PropertyDAO.class.getName());
    }

    @Override
    public Set<Property> findAllRecords() {
        return null;
    }

    @Override
    public Property findRecordByID(UUID id) {
        return null;
    }

    @Override
    public Property storeRecord(Property record) {
        return null;
    }

    @Override
    public Property updateRecord(Property record) {
        return null;
    }

    @Override
    public void destroyRecord(Property record) {

    }
}
