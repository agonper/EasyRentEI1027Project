package models.daos;

import connectors.ConnectionManager;
import models.common.Model;
import models.common.Store;
import models.transfer_objects.User;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Alberto on 18/03/2016.
 */
public abstract class DAO<T extends Model> implements Store<T> {
    protected static Logger log;

    public DAO(String className) {
        log = Logger.getLogger(className);
    }

    /**
     * =============
     * FUNCTIONALITY
     * =============
     */

    @Override
    public Set<T> findAllRecords() {
        HashSet<T> records = new HashSet<>();
        try {
            Connection connection = getConnection();
            retrieveAllRecordsFromDB(records, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
        }
        return records;
    }

    protected abstract void retrieveAllRecordsFromDB(HashSet<T> records, Connection connection);

    @Override
    public T findRecordByID(UUID id) {
        try {
            Connection connection = getConnection();
            return retrieveRecordByIDFromDB(id, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
            return null;
        }
    }

    protected abstract T retrieveRecordByIDFromDB(UUID id, Connection connection);

    @Override
    public T storeRecord(T record) {
        record.id = UUID.randomUUID();
        try {
            Connection connection = getConnection();
            insertRecordIntoDB(record, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
        }
        return record;
    }

    protected abstract void insertRecordIntoDB(T record, Connection connection);

    @Override
    public T updateRecord(T record) {
        try {
            Connection connection = getConnection();
            updateDBRecord(record, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
        }
        return record;
    }

    protected abstract void updateDBRecord(T record, Connection connection);

    @Override
    public void destroyRecord(T record) {
        try {
            Connection connection = getConnection();
            removeDBRecord(record, connection);
        } catch (ConnectException e) {
            log.severe("Connection error");
            e.printStackTrace();
        }
    }

    protected abstract void removeDBRecord(T record, Connection connection);

    /**
     * ===============
     * UTILITY METHODS
     * ===============
     */

    protected static String getInsertValuesPlaceholdersFrom(String[] fields) {
        StringBuilder placeholders = new StringBuilder();
        placeholders.append("?");
        for (int i = 1; i < fields.length; i++) {
            placeholders.append(", ?");
        }
        return placeholders.toString();
    }

    protected static String getUpdateFieldsFrom(String[] fields) {
        StringBuilder updateFields = new StringBuilder();
        updateFields.append(fields[0] + " = ?");
        for (int i = 1; i < fields.length; i++) {
            updateFields.append(", " + fields[i] + " = ?");
        }
        return updateFields.toString();
    }

    private static Connection getConnection() throws ConnectException {
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
}
