package models.daos;

import models.common.Store;
import models.transfer_objects.RoleNotFoundException;
import models.transfer_objects.User;
import models.transfer_objects.UserRole;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class UserDAO extends DAO implements Store<User> {

    private final static String USER_COLUMNS = "id, username, national_document, role, password, " +
            "name, surnames, email, phone_number, country, post_address, post_code, " +
            "sign_up_date, active, deactivated_since";

    private final static String TABLE_NAME = "users";

    public UserDAO() {
        super(UserDAO.class.getName());
    }

    /**
     * =============
     * FUNCTIONALITY
     * =============
     */

    @Override
    public Set<User> findAllRecords() {
        HashSet<User> users = new HashSet<>();
        try {
            Connection connection = getConnection();
            retrieveAllUsers(users, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findRecordByID(UUID id) {
        try {
            Connection connection = getConnection();
            return retrieveUserByID(id, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User storeRecord(User record) {
        return null;
    }

    @Override
    public User updateRecord(User record) {
        return null;
    }

    @Override
    public void destroyRecord(User record) {

    }

    /**
     * ===============
     * SUPPORT METHODS
     * ===============
     */

    private void retrieveAllUsers(HashSet<User> users, Connection connection) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String template = "SELECT %s FROM %s";
            stmt = connection.prepareStatement(String.format(template, USER_COLUMNS, TABLE_NAME));
            rs = stmt.executeQuery();

            populateUsersSet(users, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, rs);
        }
    }

    private void populateUsersSet(HashSet<User> users, ResultSet rs) throws SQLException {
        while (rs.next()) {
            User user = populateUserModelWith(rs);
            if (user != null) {
                users.add(user);
            }
        }
    }

    private User retrieveUserByID(UUID id, Connection connection) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String template = "SELECT %s FROM %s WHERE id=?";
            stmt = connection.prepareStatement(String.format(template, USER_COLUMNS, TABLE_NAME));
            stmt.setString(1, id.toString());
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = populateUserModelWith(rs);
            }

        } catch (SQLException e) {
            log.severe("Error preparing findRecordByID statement");
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, rs);
        }
        return user;
    }

    private User populateUserModelWith(ResultSet rs) throws SQLException {
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
}
