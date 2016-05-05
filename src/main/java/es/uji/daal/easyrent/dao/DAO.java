package es.uji.daal.easyrent.dao;

import es.uji.daal.easyrent.model.DomainModel;
import es.uji.daal.easyrent.services.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Alberto on 18/03/2016.
 */
@Repository
public abstract class DAO<T extends DomainModel> implements Store<T> {

    protected static Logger log;
    protected final DAOUtils daoUtils;
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DAO(String className) {
        if (log == null)
            log = Logger.getLogger(className);
        this.daoUtils = new DAOUtils(getTableColumns());
    }

    /**
     * Functionality
     */

    @Override
    public List<T> findAll() {
        String query = String.format("SELECT %s FROM %s", daoUtils.getFieldNames(), getTableName());
        return this.jdbcTemplate.query(query, createModelMapper());
    }

    @Override
    public T findOneByID(UUID id) {
        String query = String.format("SELECT %s FROM %s WHERE id=?", daoUtils.getFieldNames(), getTableName());
        return this.jdbcTemplate.queryForObject(query, new Object[] {id}, createModelMapper());
    }

    @Override
    public T storeRecord(T record) {
        String query = String.format("INSERT INTO %s(%s) VALUES(%s)", getTableName(), 
                daoUtils.getFieldNames(), daoUtils.getInsertWildcards());
        UUID uuid = UUID.randomUUID();
        record.setId(uuid);
        this.jdbcTemplate.update(query, getValues(record));
        return record;
    }

    @Override
    public void updateRecord(T record) {
        String query = String.format("UPDATE %s SET %s WHERE id=?", getTableName(), daoUtils.getUpdatePlaceholders());
        this.jdbcTemplate.update(query, getValues(record), record.getId());
    }

    @Override
    public void destroyRecord(T record) {
        String query = String.format("DELETE FROM %s WHERE id=?", getTableName());
        this.jdbcTemplate.update(query, record.getId());
    }

    /**
     * Utility
     */

    protected abstract String getTableName();

    protected abstract RowMapper<T> createModelMapper();

    protected abstract String[] getTableColumns();

    protected abstract Object[] getValues(T record);

}
