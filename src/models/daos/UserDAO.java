package models.daos;

import models.transfer_objects.RoleNotFoundException;
import models.transfer_objects.User;
import models.transfer_objects.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
            user.id = (UUID) rs.getObject("id");
            user.username = rs.getString("username");
            user.DNI = rs.getString("national_document");
            user.role = UserRole.obtainRoleFor(rs.getString("role"));
            user.password = rs.getString("password");
            user.name = rs.getString("name");
            user.surnames = rs.getString("surnames");
            user.email = rs.getString("email");
            user.phoneNumber = rs.getString("phone_number");
            user.country = rs.getString("country");
            user.postalAddress = rs.getString("post_address");
            user.postCode = rs.getInt("post_code");
            user.signUpDate = rs.getDate("sign_up_date");
            user.active = rs.getBoolean("active");
            user.deactivatedSince = rs.getDate("deactivated_since");

        } catch (RoleNotFoundException e) {
            log.warning("User entry with id " + user.id + " appears to have a role inconsistency problem");
            user = null;
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected void setStatementAttributes(User user, PreparedStatement stmt, int initialPosition) throws SQLException {
        int p = initialPosition;

        stmt.setString(p++, user.username);
        stmt.setString(p++, user.DNI);
        stmt.setString(p++, user.role.toString());
        stmt.setString(p++, user.password);
        stmt.setString(p++, user.name);
        stmt.setString(p++, user.surnames);
        stmt.setString(p++, user.email);
        stmt.setString(p++, user.phoneNumber);
        stmt.setString(p++, user.country);
        stmt.setString(p++, user.postalAddress);
        stmt.setInt(p++, user.postCode);
        stmt.setDate(p++, user.signUpDate);
        stmt.setBoolean(p++, user.active);
        stmt.setDate(p, user.deactivatedSince);
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