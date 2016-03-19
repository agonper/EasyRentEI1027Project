package models.common;

import models.transfer_objects.Model;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface StoreFactory {
    public Store<Model> getStore(Class modelClass);
}
