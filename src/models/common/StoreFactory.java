package models.common;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface StoreFactory {
    public Store<Model> getStore(Class modelClass);
}
