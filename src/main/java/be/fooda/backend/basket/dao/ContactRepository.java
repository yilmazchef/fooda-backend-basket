package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.ContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ContactRepository extends MongoRepository<ContactEntity, String> {

    @Query("{'user.eUserId' : ?0}")
    List<ContactEntity> findByUser(UUID eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<ContactEntity> findByUser(UUID eUserId, String session);

    @Query("{'eContactId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    Optional<ContactEntity> findByContactAndUser(UUID eContactId, UUID eUserId, String session);
}
