package models.daos;

import connectors.ConnectionManager;

import java.net.ConnectException;
import java.sql.Connection;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class DAO {
    private Connection getConnection() throws ConnectException {
        return ConnectionManager.getConnection();
    }

    // TODO: Implement operation cleanup common functions
}
