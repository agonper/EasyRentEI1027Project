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

        invoice.setNumber(rs.getInt("invoice_number"));
        invoice.setProposalID((UUID) rs.getObject("proposal_id"));
        invoice.setVat(rs.getInt("actual_vat"));
        invoice.setAddress(rs.getString("address"));
        invoice.setExpeditionDate(rs.getDate("invoice_date"));

        return invoice;
    }

    @Override
    protected void setStatementAttributes(Invoice record, PreparedStatement stmt, int initialPosition) throws SQLException {
        int position = initialPosition;

        stmt.setInt(position++, record.getNumber());
        stmt.setObject(position++, record.getProposalID());
        stmt.setFloat(position++, record.getVat());
        stmt.setString(position++, record.getAddress());
        stmt.setDate(position, record.getExpeditionDate());
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
