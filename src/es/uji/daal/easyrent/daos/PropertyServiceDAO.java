package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.PropertyService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServiceDAO extends DAO<PropertyService> {

    private final static String TABLE_NAME = "properties_services";

    public PropertyServiceDAO() {
        super(PropertyServiceDAO.class.getName());
    }

    private static final class PropertyServiceMapper implements RowMapper<PropertyService> {

        @Override
        public PropertyService mapRow(ResultSet rs, int i) throws SQLException {
            PropertyService ps = new PropertyService();

            ps.setId((UUID) rs.getObject("id"));
            ps.setPropertyID((UUID) rs.getObject("property_id"));
            ps.setServiceID((UUID) rs.getObject("service_id"));

            return ps;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<PropertyService> createModelMapper() {
        return new PropertyServiceMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "property_id",
                "service_id"
        };
    }

    @Override
    protected Object[] getValues(PropertyService ps) {
        return new Object[] {
                ps.getId(),
                ps.getPropertyID(),
                ps.getServiceID()
        };
    }
}
