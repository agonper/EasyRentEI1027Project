package models.daos;

import models.common.Store;
import models.transfer_objects.BookingProposal;

import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class BookingProposalDAO extends DAO implements Store<BookingProposal> {
    public BookingProposalDAO() {
        super(BookingProposalDAO.class.getName());
    }

    @Override
    public Set<BookingProposal> findAllRecords() {
        return null;
    }

    @Override
    public BookingProposal findRecordByID(UUID id) {
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
