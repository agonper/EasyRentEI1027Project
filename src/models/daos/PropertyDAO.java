package models.daos;

import models.common.Store;
import models.transfer_objects.Property;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class PropertyDAO implements Store<Property> {
    @Override
    public List<Property> findAllRecords() {
        return null;
    }

    @Override
    public Property findRecordByID(String id) {
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
