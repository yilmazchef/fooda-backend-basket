package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    List<ProductEntity> findAllByUser_ExternalUserIdAndUser_Session(Long externalUserId, String session);

    List<ProductEntity> findByUser_ExternalUserIdAndUser_SessionAndStore_ExternalStoreId(Long externalUserId, String session, Long externalStoreId);

    List<ProductEntity> findAllByUser_ExternalUserIdAndStore_ExternalStoreId(Long externalUserId, Long externalStoreId);

    Optional<ProductEntity> findByUser_ExternalUserIdAndUser_SessionAndExternalProductId(Long externalUserId, String session, Long externalProductId);

    boolean existsByUser_ExternalUserIdAndUser_SessionAndExternalProductId(Long externalUserId, String userSession, Long externalProductId);
}
