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

    //TODO: ¿service_id o id como en la variable del DAO?
    private final static String TABLE_COLUMNS = "service_id, service_name, service_value, user_id, active, " +
            "creation_date, active_since";

    private final static String TABLE_NAME = "services";

    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    @Override
    protected Service populateModelWith(ResultSet rs) throws SQLException {
        Service service = new Service();
        try {
            service.id = (UUID) rs.getObject("service_id");
            service.serviceName = rs.getString("service_name");
            service.serviceValue = rs.getString("service_value");
            service.user_id = (UUID) rs.getObject("user_id");
            service.active = rs.getBoolean("active");
            service.creationDate = rs.getDate("creation_date");
            service.activeSince = rs.getDate("active_since");
        }
        //TODO: Concretar excepción
        catch (Exception e) {
            service = null;
            e.printStackTrace();
        }
        return service;
    }

    @Override
    protected void setStatementAttributes(Service record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        //TODO: ¿Sobra el id?
        stmt.setObject(position++, record.id);
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
