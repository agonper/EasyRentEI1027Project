package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface UserRepository extends CrudRepository<User, UUID>, UserRepositoryCustom {

    User findByUsernameIgnoreCase(String username);

    @Query("select count(e)>0 from User e where lower(e.email) = lower(:email)")
    boolean existsByEmail(@Param("email") String email);

    @Query("select count(e)>0 from User e where lower(e.username) = lower(:username)")
    boolean existsByUsername(@Param("username") String username);

    @Query("select e from User e where lower(e.username) like lower( concat('%', :username, '%') )")
    List<User> findByUsernameContainedInSearchedName(@Param("username") String username);

    //TODO: Terminate
    @Query("select e from User e where lower(e.id) like lower( concat('%', :id, '%') )")
    List<User> findByIDContainedInSearchedID(@Param("id") UUID id);

    @Query("select e from User e where lower(e.Dni) like lower( concat('%', :Dni, '%') )")
    List<User> findByNIDContainedInSearchedNID(@Param("Dni") String Dni);

    @Query("select e from User e where lower(e.role) like lower( concat('%', :role, '%') ) ")
    List<User> findByRoleContainedInSearchedRole(@Param("role") String role);

    @Query("select e from User e where lower(e.name) like lower( concat('%', :name, '%') )")
    List<User> findByNameContainedInSearchedName(@Param("name") String name);

    @Query("select e from User e where lower(e.surnames) like lower( concat('%', :surnames, '%') )")
    List<User> findBySurnamesContainedInSearchedSurnames(@Param("surnames") String surnames);

    @Query("select e from User e where lower(e.email) like lower(concat('%', :email, '%') ) ")
    List<User> findByEmailContainedInSearchedEmails(@Param("email") String email);

    //TODO: Test
    @Query("select e from User e where e.phoneNumber = concat('%d', :phoneNumber, '%d') ")
    List<User> findByPhoneContainedInSearchedPhone(@Param("phoneNumber") int phoneNumber);

    @Query("select e from User e where lower(e.postalAddress) like lower(concat('%', :address, '%') ) ")
    List<User> findByAddressContainedInSearchedAddress(@Param("address") String address);

    @Query("select e from User e where lower(e.country) like lower(concat('%', :country, '%') ) ")
    List<User> findByCountryContainedInSearchedCountry(@Param("country") String country);

    //TODO: Test
    @Query("select e from User e where e.postCode = concat('%d', :postCode, '%d') ")
    List<User> findByPostCodeContainedInSearchedPostCode(@Param("postCode") int postCode);

    //TODO: Test
    @Query("select e from User e where e.signUpDate = :signUpDate")
    List<User> findBySignUpDate(@Param("signUpDate") Date signUpDate);

    //TODO: Test
    @Query("select e from User e where e.active = :active")
    List<User> findByActiveAccount(@Param("active") boolean active);

    //TODO: Test
    @Query("select e from User e where e.deactivatedSince = :deactivatedSince")
    List<User> findByDeactivatedSince(@Param("deactivatedSince") Date deactivatedSince);
}
