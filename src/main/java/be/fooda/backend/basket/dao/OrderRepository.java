package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    @Query("{'user.eUserId' : ?0}")
    List<OrderEntity> findByUser(String eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<OrderEntity> findByUser(String eUserId, String session);

    @Query("{'eStoreId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    Optional<OrderEntity> findByStoreAndUser(String eStoreId, String eUserId, String session);

    @Query("{'eStoreId' : ?0}, 'user.eUserId' : ?1}")
    List<OrderEntity> findByStoreAndUser(String eUserId, String eStoreId);

}
