package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Alberto on 15/05/2016.
 */
public interface PropertyRepositoryCustom {
    Page<Property> searchBy(String query, Pageable pageable);
}
