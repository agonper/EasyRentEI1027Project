package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.AvailabilityPeriod;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */

@Repository
public class AvailabilityPeriodDAO extends DAO<AvailabilityPeriod> {

    private final static String TABLE_NAME = "availability_of_properties";

    public AvailabilityPeriodDAO() {
        super(AvailabilityPeriodDAO.class.getName());
    }

    private static final class AvailabilityPeriodMapper implements RowMapper<AvailabilityPeriod> {

        @Override
        public AvailabilityPeriod mapRow(ResultSet rs, int i) throws SQLException {
            AvailabilityPeriod ap = new AvailabilityPeriod();

            ap.setId((UUID) rs.getObject("id"));
            ap.setPropertyID((UUID) rs.getObject("property_id"));
            ap.setStartDate(rs.getDate("start_date"));
            ap.setEndDate(rs.getDate("end_date"));

            return ap;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<AvailabilityPeriod> createModelMapper() {
        return new AvailabilityPeriodMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "property_id",
                "start_date",
                "end_date"
        };
    }

    @Override
    protected Object[] getValues(AvailabilityPeriod ap) {
        return new Object[] {
                ap.getId(),
                ap.getPropertyID(),
                ap.getStartDate(),
                ap.getEndDate()
        };
    }

    public AvailabilityPeriod getByPropertyID(UUID propertyID) {
        String query = String.format("SELECT * FROM availability_of_properties WHERE property_id=?", propertyID);
        return jdbcTemplate.queryForObject(query, new Object[] {propertyID}, createModelMapper());
    }
}
