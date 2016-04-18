package unit;

import es.uji.daal.easyrent.connectors.ConnectionManager;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by alberto on 21/03/16.
 */
public class ConnectionManagerTest {

    @Test
    public void testGetConnection() throws Exception {
        Connection connection = ConnectionManager.getConnection();
        assertFalse(connection.isClosed());
        connection.close();
    }
}