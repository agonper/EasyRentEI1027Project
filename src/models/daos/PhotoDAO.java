package models.daos;

import models.common.Store;
import models.transfer_objects.Photo;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class PhotoDAO extends DAO implements Store<Photo> {
    @Override
    public List<Photo> findAllRecords() {
        return null;
    }

    @Override
    public Photo findRecordByID(String id) {
        return null;
    }

    @Override
    public Photo storeRecord(Photo record) {
        return null;
    }

    @Override
    public Photo updateRecord(Photo record) {
        return null;
    }

    @Override
    public void destroyRecord(Photo record) {

    }
}
