package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.BookingProposal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface BookingProposalRepository extends CrudRepository<BookingProposal, UUID> {
    List<BookingProposal> findByTenant_Id(UUID tenantID);
    List<BookingProposal> findByProperty_Owner_IdOrderByDateOfCreationDesc(UUID owner);

    //TODO: Test
    @Query("select b from BookingProposal b where lower(b.property.title) like lower(concat('%', :title, '%') ) ")
    List<BookingProposal> findByPropertyTitleContainedInSearchedPropertyTitle(@Param("title") String title);

    //TODO: Test
    @Query("select b from BookingProposal b where lower(b.tenant.username) like lower(concat('%', :username, '%') ) ")
    List<BookingProposal> findByTenantUsernameContainedInSearchedTenantUsername(@Param("username") String username);

    //TODO: Test
    @Query("select b from BookingProposal b where b.startDate = :startDate")
    List<BookingProposal> findByStartDate(@Param("startDate") Date startDate);

    //TODO: Test
    @Query("select b from BookingProposal b where b.endDate = :endDate")
    List<BookingProposal> findByEndDate(@Param("endDate") Date endDate);

    //TODO: Test
    @Query("select b from BookingProposal b where lower(b.status) like lower(:status) ")
    List<BookingProposal> findByStatus(@Param("status") String status);

    //TODO: Test
    @Query("select b from BookingProposal b where lower(b.paymentReference) like lower(concat('%', :paymentReference, '%') ) ")
    List<BookingProposal> findByPaymentReferenceContainedInSearchedPaymentReference(@Param("paymentReference") String paymentReference);

    //TODO: Test
    @Query("select b from BookingProposal b where b.totalAmount = :totalAmount")
    List<BookingProposal> findByTotalAmount(@Param("totalAmount") float totalAmount);

    //TODO: Test
    @Query("select b from BookingProposal b where b.numberOfTenants = :numberOfTenants")
    List<BookingProposal> findByNumberOfTenants(@Param("numberOfTenants") int numberOfTenants);

    //TODO: Test
    @Query("select b from BookingProposal b where b.dateOfCreation = :dateOfCreation")
    List<BookingProposal> findByDateOfCreation(@Param("dateOfCreation") Date dateOfCreation);

    //TODO: Test
    @Query("select b from BookingProposal b where b.dateOfUpdate = :dateOfUpdate")
    List<BookingProposal> findByDateOfUpdate(@Param("dateOfUpdate") Date dateOfUpdate);

    //TODO: Test
    @Query("select b from BookingProposal b where b.invoice.number = :number")
    List<BookingProposal> findByInvoiceNumber(@Param("number") int number);
}
