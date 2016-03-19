package models.common;

import java.util.List;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface Store<T extends Model> {
    public List<T> findAllRecords();
    public T findRecordByID(String id);
    public T storeRecord(T record);
    public T updateRecord(T record);
    public void destroyRecord(T record);
}
