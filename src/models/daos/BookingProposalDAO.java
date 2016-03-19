package models.daos;

import models.common.Store;
import models.transfer_objects.BookingProposal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class BookingProposalDAO extends DAO<BookingProposal> {
    public BookingProposalDAO() {
        super(BookingProposalDAO.class.getName());
    }

    @Override
    protected BookingProposal populateModelWith(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void setStatementAttributes(BookingProposal record, PreparedStatement stmt, int initialPosition) throws SQLException {

    }

    @Override
    protected String getSerializedTableColumns() {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }
}
