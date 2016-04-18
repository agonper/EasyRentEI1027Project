package es.uji.daal.easyrent.models;

import es.uji.daal.easyrent.services.Store;
import es.uji.daal.easyrent.services.StoreFactory;
import es.uji.daal.easyrent.daos.DAOFactory;

import java.util.UUID;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class Model {
    private UUID id = null;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
