package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.BookingProposal;
import es.uji.daal.easyrent.models.ProposalStatus;
import es.uji.daal.easyrent.models.StatusNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class BookingProposalDAO extends DAO<BookingProposal> {

    private final static String TABLE_COLUMNS = "id, property_id, tenant_id, start_date, end_date, status, payment_reference, total_amount, number_of_tenants, date_of_creation, date_of_acceptation";

    private final static String TABLE_NAME = "booking_proposals";

    public BookingProposalDAO() {
        super(BookingProposalDAO.class.getName());
    }

    @Override
    protected BookingProposal populateModelWith(ResultSet rs) throws SQLException {
        BookingProposal bp = new BookingProposal();

        try {
            bp.setPropertyID((UUID) rs.getObject("property_id"));
            bp.setTenantID((UUID) rs.getObject("tenant_id"));
            bp.setStartDate(rs.getDate("start_date"));
            bp.setEndDate(rs.getDate("end_date"));
            bp.setStatus(ProposalStatus.obtainStatusFor(rs.getString("status")));
            bp.setPaymentReference(Integer.toString(rs.getInt("payment_reference")));
            bp.setTotalAmount(rs.getFloat("total_amount"));
            bp.setNumberOfTenants(rs.getInt("number_of_tenants"));
            bp.setDateOfCreation(rs.getDate("date_of_creation"));
            bp.setDateOfAcceptation(rs.getDate("date_of_acceptation"));
        } catch (StatusNotFoundException e) {
            log.warning("Booking proposal entry with id " + bp.getId() + " appears to have a status inconsistency problem");
            bp = null;
            e.printStackTrace();
        }

        return bp;
    }

    @Override
    protected void setStatementAttributes(BookingProposal record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.getPropertyID());
        stmt.setObject(position++, record.getTenantID());
        stmt.setDate(position++, record.getStartDate());
        stmt.setDate(position++, record.getEndDate());
        stmt.setString(position++, record.getStatus().toString());
        stmt.setString(position++, record.getPaymentReference());
        stmt.setFloat(position++, record.getTotalAmount());
        stmt.setInt(position++, record.getNumberOfTenants());
        stmt.setDate(position++, record.getDateOfCreation());
        stmt.setDate(position, record.getDateOfAcceptation());
    }

    @Override
    protected String getSerializedTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
