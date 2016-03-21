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

    private final static String TABLE_COLUMNS = "id, upload_date, filename";

    private final static String TABLE_NAME = "photos";

    public PhotoDAO() {
        super(PhotoDAO.class.getName());
    }

    @Override
    protected Photo populateModelWith(ResultSet rs) throws SQLException {
        Photo photo = new Photo();

        photo.uploadDate = rs.getDate("upload_date");
        photo.filename = rs.getString("filename");

        return photo;
    }

    @Override
    protected void setStatementAttributes(Photo record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setDate(position++, record.uploadDate);
        stmt.setString(position, record.filename);
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
