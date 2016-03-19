package models.daos;

import models.common.Store;
import models.transfer_objects.Invoice;

import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class InvoiceDAO extends DAO implements Store<Invoice> {
    public InvoiceDAO() {
        super(InvoiceDAO.class.getName());
    }

    @Override
    public Set<Invoice> findAllRecords() {
        return null;
    }

    @Override
    public Invoice findRecordByID(UUID id) {
        return null;
    }

    @Override
    public Invoice storeRecord(Invoice record) {
        return null;
    }

    @Override
    public Invoice updateRecord(Invoice record) {
        return null;
    }

    @Override
    public void destroyRecord(Invoice record) {

    }
}
