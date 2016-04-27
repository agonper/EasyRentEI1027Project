package es.uji.daal.easyrent.services;

import es.uji.daal.easyrent.models.DomainModel;

/**
 * Created by Alberto on 18/03/2016.
 */
public interface StoreFactory {
    public Store<DomainModel> getStore(Class modelClass);
}
