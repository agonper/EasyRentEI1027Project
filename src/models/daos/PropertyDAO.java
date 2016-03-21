package models.daos;

import models.transfer_objects.Property;
import models.transfer_objects.PropertyType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PropertyDAO extends DAO<Property> {

    private final static String TABLE_COLUMNS = "property_id, user_id, title, location, " +
            "rooms, capacity, beds, bathrooms, floor_space, price_per_day, creation_date, " +
            "type, description";
    private final static String TABLE_NAME = "properties";

    public PropertyDAO() {
        super(PropertyDAO.class.getName());
    }

    @Override
    protected Property populateModelWith(ResultSet rs) throws SQLException {
        Property property = new Property();
        try {
            property.id = (UUID) rs.getObject("property_id");
            property.ownerID = (UUID) rs.getObject("user_id");
            property.title = rs.getString("title");
            property.location = rs.getString("location");
            property.rooms = rs.getInt("location");
            property.capacity = rs.getInt("capacity");
            property.beds = rs.getInt("beds");
            property.bathrooms = rs.getInt("bathrooms");
            property.floorSpace = rs.getInt("floor_space");
            property.pricePerDay = rs.getFloat("price_per_day");
            property.creationDate = rs.getDate("creation_date");
            property.type = (PropertyType) rs.getObject("type");
            property.description = rs.getString("description");
        }
        //TODO: Concretar excepción
        catch (Exception e) {
            property = null;
            e.printStackTrace();
        }
        return property;
    }

    @Override
    protected void setStatementAttributes(Property record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        //TODO: ¿Sobraría el id?
        //TODO: ¿UUID como Object?
        stmt.setObject(position++, record.id);
        stmt.setObject(position++, record.ownerID);
        stmt.setString(position++, record.title);
        stmt.setString(position++, record.location);
        stmt.setInt(position++, record.rooms);
        stmt.setInt(position++, record.capacity);
        stmt.setInt(position++, record.beds);
        stmt.setInt(position++, record.bathrooms);
        stmt.setInt(position++, record.floorSpace);
        stmt.setFloat(position++, record.pricePerDay);
        stmt.setDate(position++, record.creationDate);
        stmt.setString(position++, record.description);
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
