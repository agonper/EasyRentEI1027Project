package models.daos;

import models.transfer_objects.RoleNotFoundException;
import models.transfer_objects.User;
import models.transfer_objects.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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

    /**
     * =============
     * FUNCTIONALITY
     * =============
     */

    @Override
    protected void retrieveAllRecordsFromDB(HashSet<User> users, Connection connection) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String template = "SELECT %s FROM %s";
            stmt = connection.prepareStatement(String.format(template, TABLE_COLUMNS, TABLE_NAME));
            rs = stmt.executeQuery();

            populateUsersSet(users, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, rs);
        }
    }

    @Override
    protected User retrieveRecordByIDFromDB(UUID id, Connection connection) {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String template = "SELECT %s FROM %s WHERE id=?";
            stmt = connection.prepareStatement(String.format(template, TABLE_COLUMNS, TABLE_NAME));
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

    @Override
    protected void insertRecordIntoDB(User user, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "INSERT INTO %s (%s) values (%s)";
            String placeholders = getInsertValuesPlaceholdersFrom(TABLE_COLUMNS.split(", "));
            stmt = connection.prepareStatement(String.format(template, TABLE_NAME, TABLE_COLUMNS, placeholders));
            stmt.setObject(1, user.id);
            final int INNITIAL_POSITION = 2;
            setUserVariableAttributes(user, stmt, INNITIAL_POSITION);

            stmt.execute();
        } catch (SQLException e) {
            log.severe("Error storing new user");
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    @Override
    protected void updateDBRecord(User user, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "UPDATE %s SET %s WHERE id = ?";
            String[] tableFields = TABLE_COLUMNS.split(", ");
            String fieldsToUpdate = getUpdateFieldsFrom(tableFields);

            stmt = connection.prepareStatement(String.format(template, TABLE_NAME, fieldsToUpdate));
            setUserVariableAttributes(user, stmt, 1);
            stmt.setObject(tableFields.length-1, user.id);
            stmt.execute();
        } catch (SQLException e) {
            log.severe("Couldn't update user with id: " + user.id);
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    @Override
    protected void removeDBRecord(User record, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "DELETE FROM %s WHERE id = ?";
            stmt = connection.prepareStatement(String.format(template, TABLE_NAME));
            stmt.setObject(1, record.id);
            stmt.execute();
        } catch (SQLException e) {
            log.severe("Error deleting user with id: " + record.id);
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    /**
     * ===============
     * UTILITY METHODS
     * ===============
     */

    private void populateUsersSet(HashSet<User> users, ResultSet rs) throws SQLException {
        while (rs.next()) {
            User user = populateUserModelWith(rs);
            if (user != null) {
                users.add(user);
            }
        }
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

    private void setUserVariableAttributes(User user, PreparedStatement stmt, int p) throws SQLException {
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
}
