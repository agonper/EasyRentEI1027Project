package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class ServiceDAO extends DAO<Service> {

    private final static String TABLE_COLUMNS = "id, service_name, service_value, user_id, active, " +
            "creation_date, active_since, service_proposals";

    private final static String TABLE_NAME = "es/ei1027/daal/easyrent/services";

    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    @Override
    protected Service populateModelWith(ResultSet rs) throws SQLException {
        Service service = new Service();

        service.setName(rs.getString("service_name"));
        service.setValue(rs.getString("service_value"));
        service.setUserId((UUID) rs.getObject("user_id"));
        service.setActive(rs.getBoolean("active"));
        service.setCreationDate(rs.getDate("creation_date"));
        service.setActiveSince(rs.getDate("active_since"));
        service.setServiceProposals(rs.getInt("service_proposals"));

        return service;
    }

    @Override
    protected void setStatementAttributes(Service record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setString(position++, record.getName());
        stmt.setString(position++, record.getValue());
        stmt.setObject(position++, record.getUserId());
        stmt.setBoolean(position++, record.getActive());
        stmt.setDate(position++, record.getCreationDate());
        stmt.setDate(position++, record.getActiveSince());
        stmt.setInt(position, record.getServiceProposals());
    }

    @Override
    protected String getSerializedTableColumns() {
        return TABLE_NAME;
    }

    @Override
    protected String getTableName() {
        return TABLE_COLUMNS;
    }
}
