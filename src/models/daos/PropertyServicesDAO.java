package models.daos;

import models.transfer_objects.PropertyServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Alberto on 20/03/2016.
 */
public class PropertyServicesDAO extends DAO<PropertyServices> {

    private final static String TABLE_COLUMNS = "property_id, service_id, offered_since";

    private final static String TABLE_NAME = "properties_services";

    public PropertyServicesDAO() {
        super(PropertyServicesDAO.class.getName());
    }

    @Override
    protected PropertyServices populateModelWith(ResultSet rs) throws SQLException {
        PropertyServices offered = new PropertyServices();

        try {
            offered.propertyID = (UUID) rs.getObject("property_id");
            offered.serviceID = (UUID) rs.getObject("service_id");
            offered.offeredSince = rs.getDate("offered_since");
        }
        //TODO: Concretar excepción
        catch (Exception e) {
            offered = null;
            e.printStackTrace();
        }

        return offered;
    }

    @Override
    protected void setStatementAttributes(PropertyServices record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.propertyID);
        stmt.setObject(position++, record.serviceID);
        stmt.setDate(position++, record.offeredSince);
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
