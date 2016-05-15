package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, UUID> {
    List<Invoice> findByProposal_Tenant_IdOrderByExpeditionDateDesc(UUID tenant);
}
