package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.PropertyServices;
import es.uji.daal.easyrent.models.PropertyServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServicesDAO extends DAO<PropertyServices> {

    private final static String TABLE_COLUMNS = "id, property_id, service_id";

    private final static String TABLE_NAME = "properties_services";

    public PropertyServicesDAO() {
        super(PropertyServicesDAO.class.getName());
    }

    @Override
    protected PropertyServices populateModelWith(ResultSet rs) throws SQLException {
        PropertyServices offered = new PropertyServices();

        offered.setPropertyID((UUID) rs.getObject("property_id"));
        offered.setServiceID((UUID) rs.getObject("service_id"));

        return offered;
    }

    @Override
    protected void setStatementAttributes(PropertyServices record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.getPropertyID());
        stmt.setObject(position, record.getServiceID());
    }

    @Override
    protected String getSerializedTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
