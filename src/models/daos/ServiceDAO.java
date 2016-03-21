package models.daos;

import models.transfer_objects.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class ServiceDAO extends DAO<Service> {

    private final static String TABLE_COLUMNS = "id, service_name, service_value, user_id, active, " +
            "creation_date, active_since";

    private final static String TABLE_NAME = "services";

    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    @Override
    protected Service populateModelWith(ResultSet rs) throws SQLException {
        Service service = new Service();

        service.id = (UUID) rs.getObject("id");
        service.serviceName = rs.getString("service_name");
        service.serviceValue = rs.getString("service_value");
        service.user_id = (UUID) rs.getObject("user_id");
        service.active = rs.getBoolean("active");
        service.creationDate = rs.getDate("creation_date");
        service.activeSince = rs.getDate("active_since");

        return service;
    }

    @Override
    protected void setStatementAttributes(Service record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setString(position++, record.serviceName);
        stmt.setString(position++, record.serviceValue);
        stmt.setObject(position++, record.user_id);
        stmt.setBoolean(position++, record.active);
        stmt.setDate(position++, record.creationDate);
        stmt.setDate(position++, record.activeSince);
    }

    //TODO: Estos dos métodos aparecen todos los DAO, quizás puedan abstraerse en DAO, dejando las variables vacías allí, y redefiniéndolas aquí
    @Override
    protected String getSerializedTableColumns() {
        return TABLE_NAME;
    }

    @Override
    protected String getTableName() {
        return TABLE_COLUMNS;
    }
}
