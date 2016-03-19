package models.daos;

import connectors.ConnectionManager;
import models.common.Model;
import models.common.Store;

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
        if (log == null)
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
            logConnectionError(e);
        }
        return records;
    }

    @Override
    public T findRecordByID(UUID id) {
        try {
            Connection connection = getConnection();
            return retrieveRecordByIDFromDB(id, connection);
        } catch (ConnectException e) {
            logConnectionError(e);
            return null;
        }
    }

    @Override
    public T storeRecord(T record) {
        record.id = UUID.randomUUID();
        try {
            Connection connection = getConnection();
            insertRecordIntoDB(record, connection);
        } catch (ConnectException e) {
            logConnectionError(e);
        }
        return record;
    }

    @Override
    public T updateRecord(T record) {
        try {
            Connection connection = getConnection();
            updateDBRecord(record, connection);
        } catch (ConnectException e) {
            logConnectionError(e);
        }
        return record;
    }

    @Override
    public void destroyRecord(T record) {
        try {
            Connection connection = getConnection();
            removeDBRecord(record, connection);
        } catch (ConnectException e) {
            logConnectionError(e);
        }
    }

    private void logConnectionError(Exception e) {
        log.severe("Connection error");
        e.printStackTrace();
    }

    /**
     * ===============
     * SUPPORT METHODS
     * ===============
     */

    private static Connection getConnection() throws ConnectException {
        return ConnectionManager.getConnection();
    }

    private void retrieveAllRecordsFromDB(HashSet<T> records, Connection connection) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String queryTemplate = "SELECT %s FROM %s";
            String query = generateSelectQueryString(queryTemplate);
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            populateModelsSet(records, rs);
        } catch (SQLException e) {
            log.severe("Error finding all records");
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, rs);
        }
    }

    private T retrieveRecordByIDFromDB(UUID id, Connection connection) {
        T record = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String template = "SELECT %s FROM %s WHERE id=?";
            String query = generateSelectQueryString(template);
            stmt = connection.prepareStatement(query);
            stmt.setString(1, id.toString());
            rs = stmt.executeQuery();

            if (rs.next()) {
                record = populateModelWith(rs);
            }

        } catch (SQLException e) {
            log.severe("Error preparing findRecordByID statement");
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, rs);
        }
        return record;
    }

    private void insertRecordIntoDB(T record, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "INSERT INTO %s (%s) values (%s)";
            String query = generateInsertQuery(template);
            stmt = connection.prepareStatement(query);
            stmt.setObject(1, record.id);
            final int INNITIAL_POSITION = 2;
            setStatementAttributes(record, stmt, INNITIAL_POSITION);

            stmt.execute();
        } catch (SQLException e) {
            log.severe("Error storing new record from class " + record.getClass().getName());
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    private void updateDBRecord(T record, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "UPDATE %s SET %s WHERE id = ?";
            String[] tableFields = getTableFields();
            String query = generateUpdateQueryString(template);
            stmt = connection.prepareStatement(query);

            setStatementAttributes(record, stmt, 1);
            final int LAST_STATEMENT_PLACEHOLDER = tableFields.length - 1;
            stmt.setObject(LAST_STATEMENT_PLACEHOLDER, record.id);
            stmt.execute();
        } catch (SQLException e) {
            log.severe("Couldn't update record from class " + record.getClass().getName() + " with id: " + record.id);
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    private void removeDBRecord(T record, Connection connection) {
        PreparedStatement stmt = null;
        try {
            String template = "DELETE FROM %s WHERE id = ?";
            String query = generateDeleteQueryString(template);
            stmt = connection.prepareStatement(query);
            stmt.setObject(1, record.id);
            stmt.execute();
        } catch (SQLException e) {
            log.severe("Error deleting from class " + record.getClass().getName() + " with id: " + record.id);
            e.printStackTrace();
        } finally {
            cleanupResources(stmt, null);
        }
    }

    private void populateModelsSet(HashSet<T> records, ResultSet rs) throws SQLException {
        while (rs.next()) {
            T record = populateModelWith(rs);
            if (record != null) {
                records.add(record);
            }
        }
    }

    protected abstract T populateModelWith(ResultSet rs) throws SQLException;

    protected abstract void setStatementAttributes(T record, PreparedStatement stmt, int initialPosition) throws SQLException;

    private static void cleanupResources(PreparedStatement stmt, ResultSet rs) {
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

    /**
     * ===============
     * UTILITY METHODS
     * ===============
     */

    private String generateSelectQueryString(String queryTemplate) {
        return String.format(queryTemplate, getSerializedTableColumns(), getTableName());
    }

    private String generateInsertQuery(String template) {
        String placeholders = getInsertValuesPlaceholdersFrom(getTableFields());
        return String.format(template, getTableName(), getSerializedTableColumns(), placeholders);
    }

    private String generateUpdateQueryString(String template) {
        String fieldsToUpdate = getUpdateFieldsFrom(getTableFields());
        return String.format(template, getTableName(), fieldsToUpdate);
    }

    private String generateDeleteQueryString(String template) {
        return String.format(template, getTableName());
    }

    private String[] getTableFields() {
        return getSerializedTableColumns().split(", ");
    }

    private static String getInsertValuesPlaceholdersFrom(String[] fields) {
        StringBuilder placeholders = new StringBuilder();
        placeholders.append("?");
        for (int i = 1; i < fields.length; i++) {
            placeholders.append(", ?");
        }
        return placeholders.toString();
    }

    private static String getUpdateFieldsFrom(String[] fields) {
        StringBuilder updateFields = new StringBuilder();
        updateFields.append(fields[0] + " = ?");
        for (int i = 1; i < fields.length; i++) {
            updateFields.append(", " + fields[i] + " = ?");
        }
        return updateFields.toString();
    }

    protected abstract String getSerializedTableColumns();

    protected abstract String getTableName();
}
