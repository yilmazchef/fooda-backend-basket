package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {

    @Query("{'user.eUserId' : ?0}")
    List<AddressEntity> findByUser(String eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<AddressEntity> findByUser(String eUserId, String session);

    @Query("{'eAddressId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    Optional<AddressEntity> findByAddressAndUser(String eAddressId, String eUserId, String session);

}
