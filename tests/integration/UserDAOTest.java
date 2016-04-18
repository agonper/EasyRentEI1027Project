package integration;

import models.daos.UserDAO;
import models.transfer_objects.User;
import models.transfer_objects.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alberto on 21/03/16.
 */
public class UserDAOTest {

    public UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAO();
    }

    @Test
    public void testFindAllRecords() throws Exception {
        final Set<User> users = userDAO.findAllRecords();
        assertFalse(users.isEmpty());
    }

    @Test
    public void testFullStorageCycle() throws Exception {
        // Test case
        User user = new User();
        user.username = "user1";
        user.DNI = "11111111X";
        user.role = UserRole.OWNER;
        user.password = "XXXXXXXXX";
        user.name = "User";
        user.surnames = "One Unique";
        user.email = "user.one@example.com";
        user.phoneNumber = "+34654321123";
        user.country = "spain";
        user.postalAddress = "False street, 123";
        user.postCode = 12345;
        user.signUpDate = new Date(1234564L);
        user.active = true;
        user.deactivatedSince = null;

        // Store user
        user = userDAO.storeRecord(user);
        final UUID userID = user.id;
        assertNotNull(userID);

        // Find user
        final User dbUser = userDAO.findRecordByID(userID);
        assertEquals(user.name, dbUser.name);

        // Update user
        final String newAddress = "Fake street, 456";
        user.postalAddress = newAddress;
        user = userDAO.updateRecord(user);
        assertEquals(user.postalAddress, newAddress);

        // Destroy user
        userDAO.destroyRecord(user);
        user = userDAO.findRecordByID(userID);
        assertNull(user);
    }

    @After
    public void tearDown() throws Exception {
        userDAO = null;
    }
}