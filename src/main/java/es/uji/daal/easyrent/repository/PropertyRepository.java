package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alberto on 07/05/2016.
 */
public interface PropertyRepository extends CrudRepository<Property, UUID>, PropertyRepositoryCustom {
    List<Property> findByOwner_Id(UUID ownerId);

    @Query("select p from Property p where lower(p.title) like lower( concat('%', :title, '%') ) ")
    List<Property> findByTitleContainedInSearchedTitle(@Param("title") String title);

    @Query("select p from Property p where lower(p.owner.username) like lower(concat('%', :owner, '%') ) ")
    List<Property> findByOwnerContainedInSearchedOwner(@Param("owner") String owner);

    //TODO: Test
    @Query("select p from Property p where p.id = :id ")
    List<Property> findByIDContainedInSearchedID(@Param("id") UUID id);

    //TODO: Arreglar
    //@Query("select p from Property p where p.owner.id = :ownerID ")
    //List<Property> findByOwnerIDContainedInSearchedOwnerID(@Param("id") UUID ownerID);

    @Query("select p from Property p where lower(p.location) like lower(concat('%', :location, '%') ) ")
    List<Property> findByLocationContainedInSearchedLocation(@Param("location") String location);

    @Query("select p from Property p where p.rooms = :rooms")
    List<Property> findByNumberOfRooms(@Param("rooms") int rooms);

    @Query("select p from Property p where p.capacity = :capacity")
    List<Property> findByCapacityNumber(@Param("capacity") int capacity);

    @Query("select p from Property p where p.beds = :beds")
    List<Property> findByNumberOfBeds(@Param("beds") int beds);

    @Query("select p from Property p where p.bathrooms = :bathrooms")
    List<Property> findByNumberOfBathrooms(@Param("bathrooms") int bathrooms);

    @Query("select p from Property p where p.floorSpace = :floorSpace")
    List<Property> findByFloorSpace(@Param("floorSpace") int floorSpace);

    @Query("select p from Property p where p.pricePerDay = :pricePerDay")
    List<Property> findByPricePerDay(@Param("pricePerDay") float pricePerDay);

    @Query("select p from Property p where p.creationDate = :creationDate")
    List<Property> findByCreationDate(@Param("creationDate") Date creationDate);

    //TODO: Test
    @Query("select p from Property p where lower(p.type) like lower(concat('%', :type, '%') ) ")
    List<Property> findByTypeContainedInSearchedType(@Param("type") String type);

    //TODO: Test
    @Query("select p from Property p where lower(p.description) like lower(concat('%', :description, '%') ) ")
    List<Property> findByDescriptionContainedInSearchedDescription(@Param("description") String description);
}
