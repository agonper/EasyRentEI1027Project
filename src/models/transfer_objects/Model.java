package models.transfer_objects;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class Model {
    public String id = null;

    public Model save() {
        if (isNew()) {
            createRecord();
        } else {
            updateRecord();
        }
        return this;
    }

    private boolean isNew() {
        return id == null;
    }

    private void createRecord() {
        // TODO: Implement DAO call
    }

    private void updateRecord() {
        // TODO: Implement DAO call
    }

    public void destroy() {
        // TODO: Implement DAO call
    }
}
