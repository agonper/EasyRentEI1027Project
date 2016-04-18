package es.uji.daal.easyrent.services;

import es.uji.daal.easyrent.models.Model;
import es.uji.daal.easyrent.models.Model;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface StoreFactory {
    public Store<Model> getStore(Class modelClass);
}
