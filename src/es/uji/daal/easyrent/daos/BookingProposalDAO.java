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
            bp.propertyID = (UUID) rs.getObject("property_id");
            bp.tenantID = (UUID) rs.getObject("tenant_id");
            bp.startDate = rs.getDate("start_date");
            bp.endDate = rs.getDate("end_date");
            bp.status = ProposalStatus.obtainStatusFor(rs.getString("status"));
            bp.paymentReference = Integer.toString(rs.getInt("payment_reference"));
            bp.totalAmount = rs.getFloat("total_amount");
            bp.numberOfTenants = rs.getInt("number_of_tenants");
            bp.dateOfCreation = rs.getDate("date_of_creation");
            bp.dateOfAcceptation = rs.getDate("date_of_acceptation");
        } catch (StatusNotFoundException e) {
            log.warning("Booking proposal entry with id " + bp.id + " appears to have a status inconsistency problem");
            bp = null;
            e.printStackTrace();
        }

        return bp;
    }

    @Override
    protected void setStatementAttributes(BookingProposal record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setObject(position++, record.propertyID);
        stmt.setObject(position++, record.tenantID);
        stmt.setDate(position++, record.startDate);
        stmt.setDate(position++, record.endDate);
        stmt.setObject(position++, record.status);
        stmt.setString(position++, record.paymentReference);
        stmt.setFloat(position++, record.totalAmount);
        stmt.setInt(position++, record.numberOfTenants);
        stmt.setDate(position++, record.dateOfCreation);
        stmt.setDate(position, record.dateOfAcceptation);
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
