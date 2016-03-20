package models.daos;

import models.transfer_objects.PropertyServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServicesDAO extends DAO<PropertyServices> {
    public PropertyServicesDAO() {
        super(PropertyServicesDAO.class.getName());
    }

    @Override
    protected PropertyServices populateModelWith(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void setStatementAttributes(PropertyServices record, PreparedStatement stmt, int initialPosition) throws SQLException {

    }

    @Override
    protected String getSerializedTableColumns() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }
}
