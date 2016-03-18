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

    public static Connection getConnection() throws ConnectException {
        try {
            if (connection != null && !connection.isClosed())
                return connection;

            Class.forName(DRIVER_NAME);
            log.fine("PostgreSQL JDBC Driver Registered!");

            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(JDBC_PROPERTIES);
            if (stream == null)
                log.severe("File " + JDBC_PROPERTIES + " not found");
            else {
                try {
                    props.load(stream);
                }
                catch(IOException e) {
                    log.severe("No read access for file " + JDBC_PROPERTIES + " " + e.getMessage());
                    e.printStackTrace();
                }
            }
            connection = DriverManager.getConnection(URL, props);

            if (connection != null) {
                log.fine("DB connection stabilised");
            } else {
                log.severe("DB connection failed!");
            }
            return connection;
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

    private static ConnectException sendException(String errorMessage) {
        log.severe(errorMessage);
        return new ConnectException(errorMessage);
    }
}
