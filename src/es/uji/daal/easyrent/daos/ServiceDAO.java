package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Service;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
@Repository
public class ServiceDAO extends DAO<Service> {

    private final static String TABLE_NAME = "services";

    public ServiceDAO() {
        super(ServiceDAO.class.getName());
    }

    private static final class ServiceMapper implements RowMapper<Service> {

        @Override
        public Service mapRow(ResultSet rs, int i) throws SQLException {
            Service service = new Service();

            service.setId((UUID) rs.getObject("id"));
            service.setName(rs.getString("service_name"));
            service.setValue(rs.getString("service_value"));
            service.setUserId((UUID) rs.getObject("user_id"));
            service.setActive(rs.getBoolean("active"));
            service.setCreationDate(rs.getDate("creation_date"));
            service.setActiveSince(rs.getDate("active_since"));
            service.setServiceProposals(rs.getInt("service_proposals"));

            return service;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<Service> createModelMapper() {
        return new ServiceMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "service_name",
                "service_value",
                "user_id",
                "active",
                "creation_date",
                "active_since",
                "service_proposals"
        };
    }

    @Override
    protected Object[] getValues(Service service) {
        return new Object[] {
                service.getId(),
                service.getName(),
                service.getValue(),
                service.getUserId(),
                service.getActive(),
                service.getCreationDate(),
                service.getActiveSince(),
                service.getServiceProposals()
        };
    }
}
