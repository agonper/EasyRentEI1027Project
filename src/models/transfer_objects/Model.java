package models.transfer_objects;

import models.common.Store;
import models.common.StoreFactory;
import models.daos.DAOFactory;

import java.util.UUID;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class Model {
    public UUID id = null;

    /**
     * =============
     * FUNCTIONALITY
     * =============
     */

    public Model save() {
        if (isNew()) {
            createRecord();
        } else {
            updateRecord();
        }
        return this;
    }


    public void destroy() {
        Store<Model> store = getStore();
        store.destroyRecord(this);
    }

    /**
     * ===============
     * SUPPORT METHODS
     * ===============
     */

    private boolean isNew() {
        return id == null;
    }


    private void createRecord() {
        Store<Model> store = getStore();
        store.storeRecord(this);
    }


    private void updateRecord() {
        Store<Model> store = getStore();
        store.updateRecord(this);
    }


    private Store<Model> getStore() {
        StoreFactory storeFactory = getStoreFactory();
        return storeFactory.getStore(this.getClass());
    }

    protected StoreFactory getStoreFactory() {
        return new DAOFactory();
    }
}
