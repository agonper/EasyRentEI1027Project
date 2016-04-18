package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class InvoiceDAO extends DAO<Invoice> {

    private final static String TABLE_COLUMNS = "id, invoice_number, proposal_id, actual_vat, address, invoice_date";

    private final static String TABLE_NAME = "invoices";

    public InvoiceDAO() {
        super(InvoiceDAO.class.getName());
    }

    @Override
    protected Invoice populateModelWith(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();

        invoice.number = rs.getInt("invoice_number");
        invoice.proposalID = (UUID) rs.getObject("proposal_id");
        invoice.vat = rs.getInt("actual_vat");
        invoice.address = rs.getString("address");
        invoice.expeditionDate = rs.getDate("invoice_date");

        return invoice;
    }

    @Override
    protected void setStatementAttributes(Invoice record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setInt(position++, record.number);
        stmt.setObject(position++, record.proposalID);
        stmt.setFloat(position++, record.vat);
        stmt.setString(position++, record.address);
        stmt.setDate(position, record.expeditionDate);
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
