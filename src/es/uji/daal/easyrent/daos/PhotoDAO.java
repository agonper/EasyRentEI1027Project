package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Photo;
import es.uji.daal.easyrent.models.Photo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PhotoDAO extends DAO<Photo> {

    private final static String TABLE_COLUMNS = "id, upload_date, filename, property_id, user_id";

    private final static String TABLE_NAME = "photos";

    public PhotoDAO() {
        super(PhotoDAO.class.getName());
    }

    @Override
    protected Photo populateModelWith(ResultSet rs) throws SQLException {
        Photo photo = new Photo();

        photo.setPropertyID((UUID) rs.getObject("property_id"));
        photo.setUserID((UUID) rs.getObject("user_id"));
        photo.setUploadDate(rs.getDate("upload_date"));
        photo.setFilename(rs.getString("filename"));

        return photo;
    }

    @Override
    protected void setStatementAttributes(Photo record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.getPropertyID());
        stmt.setObject(position++, record.getUserID());
        stmt.setDate(position++, record.getUploadDate());
        stmt.setString(position, record.getFilename());
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
