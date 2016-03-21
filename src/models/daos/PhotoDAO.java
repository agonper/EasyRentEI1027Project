package models.daos;

import models.transfer_objects.Photo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PhotoDAO extends DAO<Photo> {

    private final static String TABLE_COLUMNS = "photo_id, upload_date, filename";

    private final static String TABLE_NAME = "photos";

    public PhotoDAO() {
        super(PhotoDAO.class.getName());
    }

    @Override
    protected Photo populateModelWith(ResultSet rs) throws SQLException {
        Photo loaded = new Photo();

        try {
            loaded.id = (UUID) rs.getObject("photo_id");
            loaded.uploadDate = rs.getDate("upload_date");
            loaded.filename = rs.getString("filename");
        }
        //TODO: Comprobar excepción
        catch(Exception e) {
            loaded = null;
            e.printStackTrace();
        }

        return loaded;
    }

    @Override
    protected void setStatementAttributes(Photo record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        //TODO: ¿Sobraría el id?
        stmt.setObject(position++, record.id);
        stmt.setDate(position++, record.uploadDate);
        stmt.setString(position++, record.filename);
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
