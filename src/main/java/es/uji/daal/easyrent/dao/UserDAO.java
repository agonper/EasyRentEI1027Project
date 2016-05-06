package es.uji.daal.easyrent.dao;

import es.uji.daal.easyrent.model.RoleNotFoundException;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.model.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
@Repository
public class UserDAO extends DAO<User> {

    private final static String TABLE_NAME = "users";

    public UserDAO() {
        super(UserDAO.class.getName());
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            try {
                user.setId((UUID) rs.getObject("id"));
                user.setUsername(rs.getString("username"));
                user.setDni(rs.getString("national_document"));
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
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "username",
                "national_document",
                "role",
                "password",
                "name",
                "surnames",
                "email",
                "phone_number",
                "country",
                "post_address",
                "post_code",
                "sign_up_date",
                "active",
                "deactivated_since"
        };
    }

    @Override
    protected Object[] getValues(User user) {
        return new Object[] {
                user.getId(),
                user.getUsername(),
                user.getDni(),
                user.getRole().toString(),
                user.getPassword(),
                user.getName(),
                user.getSurnames(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCountry(),
                user.getPostalAddress(),
                user.getPostCode(),
                user.getSignUpDate(),
                user.getActive(),
                user.getDeactivatedSince()
        };
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<User> createModelMapper() {
        return new UserMapper();
    }

    public User findByUsername(String username) {
        String query = String.format("SELECT * FROM users WHERE username=?", username);
        return jdbcTemplate.queryForObject(query, new Object[] {username}, createModelMapper());
    }
}