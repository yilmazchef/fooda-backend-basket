package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findAllByUser_ExternalUserIdAndUser_Session(Long externalUserId, String session);

    Optional<OrderEntity> findByUser_ExternalUserIdAndUser_SessionAndStore_ExternalStoreId(Long externalUserId, String session, Long externalStoreId);

    List<OrderEntity> findAllByUser_ExternalUserIdAndStore_ExternalStoreId(Long externalUserId, Long externalStoreId);

    boolean existsByStore_ExternalStoreIdAndUser_ExternalUserIdAndUser_Session(Long externalStoreId, Long externalUserId, String userSession);
}
