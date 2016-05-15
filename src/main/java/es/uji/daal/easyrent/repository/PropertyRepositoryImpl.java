package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Alberto on 15/05/2016.
 */
public class PropertyRepositoryImpl implements PropertyRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<Property> searchBy(String query, Pageable pageable) {

        return new PageImpl<Property>(new ArrayList<>(), pageable, 0);
    }
}
