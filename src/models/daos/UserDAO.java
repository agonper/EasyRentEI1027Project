package models.daos;

import models.common.Store;
import models.transfer_objects.User;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class UserDAO implements Store<User> {
    @Override
    public List<User> findAllRecords() {
        return null;
    }

    @Override
    public User findRecordByID(String id) {
        return null;
    }

    @Override
    public User storeRecord(User record) {
        return null;
    }

    @Override
    public User updateRecord(User record) {
        return null;
    }

    @Override
    public void destroyRecord(User record) {

    }
}
