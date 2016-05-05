package es.uji.daal.easyrent.model;

import es.uji.daal.easyrent.services.Store;
import es.uji.daal.easyrent.services.StoreFactory;
import es.uji.daal.easyrent.dao.DAOFactory;

import java.util.UUID;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class DomainModel {
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

    public DomainModel save() {
        if (isNew()) {
            createRecord();
        } else {
            updateRecord();
        }
        return this;
    }


    public void destroy() {
        Store<DomainModel> store = getStore();
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
        Store<DomainModel> store = getStore();
        store.storeRecord(this);
    }


    private void updateRecord() {
        Store<DomainModel> store = getStore();
        store.updateRecord(this);
    }


    private Store<DomainModel> getStore() {
        StoreFactory storeFactory = getStoreFactory();
        return storeFactory.getStore(this.getClass());
    }

    protected StoreFactory getStoreFactory() {
        return new DAOFactory();
    }
}
