package connectors;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionManager {
    private static Logger log = Logger.getLogger(ConnectionManager.class.getName());

    private final static String URL = "jdbc:postgresql://db-aules.uji.es/al286290";
    private final static String DRIVER_NAME = "org.postgresql.Driver";
    private static Connection connection;
    private final static String JDBC_PROPERTIES = "./jdbc.properties";

    /**
     * =====================
     * === FUNCTIONALITY ===
     * =====================
     */

    public static Connection getConnection() throws ConnectException {
        try {
            return retrieveExistingConnectionOrCreateNewOne();
        } catch (SQLException e) {
            String errorMessage = "SQLException while connecting to the database";
            e.printStackTrace();
            throw sendException(errorMessage);
        } catch (ClassNotFoundException e) {
            String errorMessage = "The class driver couldn't be found";
            e.printStackTrace();
            throw sendException(errorMessage);
        }
    }

    /**
     * =======================
     * === SUPPORT METHODS ===
     * =======================
     */

    private static Connection retrieveExistingConnectionOrCreateNewOne() throws SQLException, ClassNotFoundException {
        if (isConnectionInitialized())
            return connection;

        retrieveDBDriver();
        initializeConnection();
        logConnectionStatus();

        return connection;
    }



    private static ConnectException sendException(String errorMessage) {
        log.severe(errorMessage);
        return new ConnectException(errorMessage);
    }



    private static boolean isConnectionInitialized() throws SQLException {
        return connection != null && !connection.isClosed();
    }



    private static void retrieveDBDriver() throws ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        log.fine("PostgreSQL JDBC Driver Registered!");
    }



    private static void initializeConnection() throws SQLException {
        Properties props = getProperties();
        connection = DriverManager.getConnection(URL, props);
    }



    private static void logConnectionStatus() throws SQLException {
        if (isConnectionInitialized()) {
            log.fine("DB connection stabilised");
        } else {
            log.severe("DB connection failed!");
        }
    }



    private static Properties getProperties() {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(JDBC_PROPERTIES);

        if (stream == null)
            log.severe("File " + JDBC_PROPERTIES + " not found");
        else {
            loadPropertiesFromFile(props, stream);
        }
        return props;
    }



    private static void loadPropertiesFromFile(Properties props, InputStream stream) {
        try {
            props.load(stream);
        }
        catch(IOException e) {
            log.severe("No read access for file " + JDBC_PROPERTIES + " " + e.getMessage());
            e.printStackTrace();
        }
    }


}
