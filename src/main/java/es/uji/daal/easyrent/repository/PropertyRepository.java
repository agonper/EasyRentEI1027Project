package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface PropertyRepository extends CrudRepository<Property, UUID>, PropertyRepositoryCustom {
    List<Property> findByOwner_Id(UUID ownerId);
}
