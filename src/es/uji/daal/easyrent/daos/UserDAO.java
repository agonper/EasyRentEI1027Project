package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.RoleNotFoundException;
import es.uji.daal.easyrent.models.User;
import es.uji.daal.easyrent.models.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alberto on 17/03/16.
 */
public class UserDAO extends DAO<User> {

    private final static String TABLE_COLUMNS = "id, username, national_document, role, password, " +
            "name, surnames, email, phone_number, country, post_address, post_code, " +
            "sign_up_date, active, deactivated_since";

    private final static String TABLE_NAME = "users";

    public UserDAO() {
        super(UserDAO.class.getName());
    }

    @Override
    protected User populateModelWith(ResultSet rs) throws SQLException {
        User user = new User();
        try {
            user.setUsername(rs.getString("username"));
            user.setDNI(rs.getString("national_document"));
            user.setRole(UserRole.obtainRoleFor(rs.getString("role")));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurnames(rs.getString("surnames"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setCountry(rs.getString("country"));
            user.setPostalAddress(rs.getString("post_address"));
            user.setPostCode(rs.getInt("post_code"));
            user.setSignUpDate(rs.getDate("sign_up_date"));
            user.setActive(rs.getBoolean("active"));
            user.setDeactivatedSince(rs.getDate("deactivated_since"));

        } catch (RoleNotFoundException e) {
            log.warning("User entry with id " + user.getId() + " appears to have a role inconsistency problem");
            user = null;
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected void setStatementAttributes(User user, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setString(position++, user.getUsername());
        stmt.setString(position++, user.getDNI());
        stmt.setString(position++, user.getRole().toString());
        stmt.setString(position++, user.getPassword());
        stmt.setString(position++, user.getName());
        stmt.setString(position++, user.getSurnames());
        stmt.setString(position++, user.getEmail());
        stmt.setString(position++, user.getPhoneNumber());
        stmt.setString(position++, user.getCountry());
        stmt.setString(position++, user.getPostalAddress());
        stmt.setInt(position++, user.getPostCode());
        stmt.setDate(position++, user.getSignUpDate());
        stmt.setBoolean(position++, user.getActive());
        stmt.setDate(position, user.getDeactivatedSince());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getSerializedTableColumns() {
        return TABLE_COLUMNS;
    }
}