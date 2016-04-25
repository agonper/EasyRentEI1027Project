package es.uji.daal.easyrent.daos;

import es.uji.daal.easyrent.models.Invoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */
public class InvoiceDAO extends DAO<Invoice> {

    private final static String TABLE_NAME = "invoices";

    public InvoiceDAO() {
        super(InvoiceDAO.class.getName());
    }

    private static final class InvoiceMapper implements RowMapper<Invoice> {

        @Override
        public Invoice mapRow(ResultSet rs, int i) throws SQLException {
            Invoice invoice = new Invoice();

            invoice.setId((UUID) rs.getObject("id"));
            invoice.setNumber(rs.getInt("invoice_number"));
            invoice.setProposalID((UUID) rs.getObject("proposal_id"));
            invoice.setVat(rs.getInt("actual_vat"));
            invoice.setAddress(rs.getString("address"));
            invoice.setExpeditionDate(rs.getDate("invoice_date"));

            return invoice;
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RowMapper<Invoice> createModelMapper() {
        return new InvoiceMapper();
    }

    @Override
    protected String[] getTableColumns() {
        return new String[] {
                "id",
                "invoice_number",
                "proposal_id",
                "actual_vat",
                "address",
                "invoice_date"
        };
    }

    @Override
    protected Object[] getValues(Invoice invoice) {
        return new Object[] {
                invoice.getId(),
                invoice.getNumber(),
                invoice.getProposalID(),
                invoice.getVat(),
                invoice.getAddress(),
                invoice.getExpeditionDate()
        };
    }
}
