package models.daos;

import models.common.Store;
import models.transfer_objects.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class ServiceDAO extends DAO<Service> {
    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    @Override
    protected Service populateModelWith(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void setStatementAttributes(Service record, PreparedStatement stmt, int initialPosition) throws SQLException {

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
