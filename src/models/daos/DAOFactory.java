package models.daos;

import models.common.Model;
import models.common.Store;
import models.common.StoreFactory;

import java.util.logging.Logger;

/**
 * Created by Alberto on 18/03/2016.
 */
public class DAOFactory implements StoreFactory {

    private static final String STORE_SUFFIX = "DAO";
    private static Logger log = Logger.getLogger(DAOFactory.class.getName());

    /**
     * =====================
     * === FUNCTIONALITY ===
     * =====================
     */

    @Override
    public Store<Model> getStore(Class modelClass) {
        String modelName = getModelName(modelClass);
        Class storeClass = loadStoreClassFor(modelName);

        return getStoreInstanceFor(storeClass);
    }

    /**
     * =======================
     * === SUPPORT METHODS ===
     * =======================
     */

    private String getModelName(Class modelClass) {
        String fullClassName = modelClass.getName();
        String[] nameParts = fullClassName.split(".");

        final int LAST_INDEX = nameParts.length - 1;
        return nameParts[LAST_INDEX];
    }


    private Class loadStoreClassFor(String modelName) {
        String packageName = getPackageName();
        Class storeClass = null;
        try {
            storeClass = Class.forName(packageName + "." + modelName + STORE_SUFFIX);
        } catch (ClassNotFoundException e) {
            log.severe("Could not find store class for name: " + modelName + STORE_SUFFIX);
            e.printStackTrace();
        }
        return storeClass;
    }


    private String getPackageName() {
        return this.getClass().getPackage().getName();
    }


    private Store<Model> getStoreInstanceFor(Class storeClass) {
        Store<Model> store = null;
        try {
            store = (Store<Model>) storeClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.severe("Could not create an store instance for class: " + storeClass.getName());
            e.printStackTrace();
        }
        return store;
    }
}
