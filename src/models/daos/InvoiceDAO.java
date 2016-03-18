package models.daos;

import models.common.Store;
import models.transfer_objects.Invoice;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class InvoiceDAO implements Store<Invoice> {
    @Override
    public List<Invoice> findAllRecords() {
        return null;
    }

    @Override
    public Invoice findRecordByID(String id) {
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
