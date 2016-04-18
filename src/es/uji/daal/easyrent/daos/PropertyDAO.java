package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Property;
import es.uji.daal.easyrent.models.PropertyType;
import es.uji.daal.easyrent.models.PropertyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PropertyDAO extends DAO<Property> {

    private final static String TABLE_COLUMNS = "id, owner_id, title, location, " +
            "rooms, capacity, beds, bathrooms, floor_space, price_per_day, creation_date, " +
            "type, description";
    private final static String TABLE_NAME = "properties";

    public PropertyDAO() {
        super(PropertyDAO.class.getName());
    }

    @Override
    protected Property populateModelWith(ResultSet rs) throws SQLException {
        Property property = new Property();

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
        property.setType((PropertyType) rs.getObject("type"));
        property.setDescription(rs.getString("description"));

        return property;
    }

    @Override
    protected void setStatementAttributes(Property record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.getOwnerID());
        stmt.setString(position++, record.getTitle());
        stmt.setString(position++, record.getLocation());
        stmt.setInt(position++, record.getRooms());
        stmt.setInt(position++, record.getCapacity());
        stmt.setInt(position++, record.getBeds());
        stmt.setInt(position++, record.getBathrooms());
        stmt.setInt(position++, record.getFloorSpace());
        stmt.setFloat(position++, record.getPricePerDay());
        stmt.setDate(position++, record.getCreationDate());
        stmt.setString(position++, record.getType().toString());
        stmt.setString(position, record.getDescription());
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
