package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Service;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface ServiceRepository extends CrudRepository<Service, UUID> {

    @Modifying
    @Transactional
    @Query("update Service s set s.serviceProposals = s.serviceProposals+1")
    void incrementServiceProposalsByOne();
}