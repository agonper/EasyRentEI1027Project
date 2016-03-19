package models.daos;

import connectors.ConnectionManager;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class DAO {
    protected static Logger log;

    public DAO(String className) {
        log = Logger.getLogger(className);
    }

    protected Connection getConnection() throws ConnectException {
        return ConnectionManager.getConnection();
    }

    protected void cleanupResources(PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                log.warning("Error closing ResultSet");
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.warning("Error closing PreparedStatement");
                e.printStackTrace();
            }
        }
    }

    // TODO: Implement operation cleanup common functions
}
