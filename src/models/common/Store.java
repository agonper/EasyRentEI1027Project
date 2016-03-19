package models.common;

import models.transfer_objects.Model;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface Store<T extends Model> {
    public Set<T> findAllRecords();
    public T findRecordByID(UUID id);
    public T storeRecord(T record);
    public T updateRecord(T record);
    public void destroyRecord(T record);
}
