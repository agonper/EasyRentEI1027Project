package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class PhotoDAO extends DAO<Photo> {

    private final static String TABLE_NAME = "photos";

    public PhotoDAO() {
        super(PhotoDAO.class.getName());
    }

    private static final class PhotoMapper implements RowMapper<Photo> {

        @Override
        public Photo mapRow(ResultSet rs, int i) throws SQLException {
            Photo photo = new Photo();

            photo.setId((UUID) rs.getObject("id"));
            photo.setPropertyID((UUID) rs.getObject("property_id"));
            photo.setUserID((UUID) rs.getObject("user_id"));
            photo.setUploadDate(rs.getDate("upload_date"));
            photo.setFilename(rs.getString("filename"));

            return photo;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<Photo> createModelMapper() {
        return new PhotoMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "upload_date",
                "filename",
                "property_id",
                "user_id"
        };
    }

    @Override
    protected Object[] getValues(Photo photo) {
        return new Object[] {
                photo.getId(),
                photo.getPropertyID(),
                photo.getUserID(),
                photo.getUploadDate(),
                photo.getFilename()
        };
    }
}
