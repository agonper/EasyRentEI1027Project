package es.uji.daal.easyrent.services;

import es.uji.daal.easyrent.model.DomainModel;

import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface Store<T extends DomainModel> {
    public List<T> findAll();
    public T findOneByID(UUID id);
    public T storeRecord(T record);
    public void updateRecord(T record);
    public void destroyRecord(T record);
}
