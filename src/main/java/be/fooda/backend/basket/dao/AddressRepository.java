package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<AddressEntity, String> {

    List<AddressEntity> findByUser_ExternalUserId(Long externalUserId);

    List<AddressEntity> findAllByUser_ExternalUserIdAndUser_Session(Long externalUserId, String session);

    Optional<AddressEntity> findByExternalAddressIdAndUser_ExternalUserIdAndUser_Session(Long externalAddressId, Long externalUserId, String session);

    boolean existsByExternalAddressIdAndUser_ExternalUserIdAndUser_Session(Long externalAddressId, Long externalUserId, String session);

}
