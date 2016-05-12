package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface UserRepository extends CrudRepository<User, UUID>, UserRepositoryCustom {

    User findByUsernameIgnoreCase(String username);

    @Query("select count(e)>0 from User e where lower(e.username) = lower(:username)")
    boolean existsByUsername(@Param("username") String username);
}
