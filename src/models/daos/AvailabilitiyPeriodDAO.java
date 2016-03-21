package models.daos;

import models.transfer_objects.AvailabilityPeriod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class AvailabilitiyPeriodDAO extends DAO<AvailabilityPeriod> {

    private final static String TABLE_COLUMNS = "id, property_id, start_date, end_date";

    private final static String TABLE_NAME = "availability_of_properties";

    public AvailabilitiyPeriodDAO() {
        super(AvailabilitiyPeriodDAO.class.getName());
    }

    @Override
    protected AvailabilityPeriod populateModelWith(ResultSet rs) throws SQLException {
        AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod();

        availabilityPeriod.propertyID = (UUID) rs.getObject("property_id");
        availabilityPeriod.startDate = rs.getDate("start_date");
        availabilityPeriod.endDate = rs.getDate("end_date");

        return availabilityPeriod;
    }

    @Override
    protected void setStatementAttributes(AvailabilityPeriod record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int p = initialPosition;

        stmt.setObject(p++, record.propertyID);
        stmt.setDate(p++, record.startDate);
        stmt.setDate(p, record.endDate);

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
