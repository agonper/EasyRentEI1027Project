package models.daos;

import models.common.Store;
import models.transfer_objects.BookingProposal;

import java.util.List;

/**
 * Created by alberto on 17/03/16.
 */
public class BookingProposalDAO implements Store<BookingProposal> {
    @Override
    public List<BookingProposal> findAllRecords() {
        return null;
    }

    @Override
    public BookingProposal findRecordByID(String id) {
        return null;
    }

    @Override
    public BookingProposal storeRecord(BookingProposal record) {
        return null;
    }

    @Override
    public BookingProposal updateRecord(BookingProposal record) {
        return null;
    }

    @Override
    public void destroyRecord(BookingProposal record) {

    }
}
