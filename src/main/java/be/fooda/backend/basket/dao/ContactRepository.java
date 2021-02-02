package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.ContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface ContactRepository extends MongoRepository<ContactEntity, String> {

    List<ContactEntity> findByUser_ExternalUserId(Long externalUserId);

    List<ContactEntity> findAllByUser_ExternalUserIdAndUser_Session(Long externalUserId, String session);

    Optional<ContactEntity> findByExternalContactIdAndUser_ExternalUserIdAndUser_Session(Long externalContactId, Long externalUserId, String userSession);

    boolean existsByExternalContactIdAndUser_ExternalUserIdAndUser_Session(Long externalContactId, Long externalUserId, String userSession);
}
