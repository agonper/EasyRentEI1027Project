package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Property;
import es.uji.daal.easyrent.models.PropertyType;
import es.uji.daal.easyrent.models.TypeNotFoundException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PropertyDAO extends DAO<Property> {

    private final static String TABLE_NAME = "properties";

    public PropertyDAO() {
        super(PropertyDAO.class.getName());
    }

    private static final class PropertyMapper implements RowMapper<Property> {

        @Override
        public Property mapRow(ResultSet rs, int i) throws SQLException {
            Property property = new Property();

            try {
                property.setId((UUID) rs.getObject("id"));
                property.setOwnerID((UUID) rs.getObject("owner_id"));
                property.setTitle(rs.getString("title"));
                property.setLocation(rs.getString("location"));
                property.setRooms(rs.getInt("rooms"));
                property.setCapacity(rs.getInt("capacity"));
                property.setBeds(rs.getInt("beds"));
                property.setBathrooms(rs.getInt("bathrooms"));
                property.setFloorSpace(rs.getInt("floor_space"));
                property.setPricePerDay(rs.getFloat("price_per_day"));
                property.setCreationDate(rs.getDate("creation_date"));
                property.setType(PropertyType.obtainTypeFor(rs.getString("type")));
                property.setDescription(rs.getString("description"));
            } catch (TypeNotFoundException e) {
                log.warning("Property entry with id " + property.getId() + " appears to have a role inconsistency problem");
                property = null;
                e.printStackTrace();
            }

            return property;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<Property> createModelMapper() {
        return new PropertyMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "owner_id",
                "title",
                "location",
                "rooms",
                "capacity",
                "beds",
                "bathrooms",
                "floor_space",
                "price_per_day",
                "creation_date",
                "type",
                "description"
        };
    }

    @Override
    protected Object[] getValues(Property property) {
        return new Object[] {
                property.getId(),
                property.getOwnerID(),
                property.getTitle(),
                property.getLocation(),
                property.getRooms(),
                property.getCapacity(),
                property.getBeds(),
                property.getBathrooms(),
                property.getFloorSpace(),
                property.getPricePerDay(),
                property.getCreationDate(),
                property.getType().toString(),
                property.getDescription()
        };
    }
}
