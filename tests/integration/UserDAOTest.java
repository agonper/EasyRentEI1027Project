package integration;

import es.uji.daal.easyrent.daos.UserDAO;
import es.uji.daal.easyrent.models.User;
import es.uji.daal.easyrent.models.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
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
        user.setUsername("user1");
        user.setDNI("11111111X");
        user.setRole(UserRole.OWNER);
        user.setPassword("XXXXXXXXX");
        user.setName("User");
        user.setSurnames("One Unique");
        user.setEmail("user.one@example.com");
        user.setPhoneNumber("+34654321123");
        user.setCountry("spain");
        user.setPostalAddress("False street, 123");
        user.setPostCode(12345);
        user.setSignUpDate(new Date(1234564L));
        user.setActive(true);
        user.setDeactivatedSince(null);

        // Store user
        user = userDAO.storeRecord(user);
        final UUID userID = user.getId();
        assertNotNull(userID);

        // Find user
        final User dbUser = userDAO.findRecordByID(userID);
        assertEquals(user.getName(), dbUser.getName());

        // Update user
        final String newAddress = "Fake street, 456";
        user.setPostalAddress(newAddress);
        user = userDAO.updateRecord(user);
        assertEquals(user.getPostalAddress(), newAddress);

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