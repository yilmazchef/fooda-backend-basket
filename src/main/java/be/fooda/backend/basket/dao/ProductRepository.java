package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    @Query("{'user.eUserId' : ?0}")
    List<ProductEntity> findByUser(UUID eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<ProductEntity> findByUser(UUID eUserId, String session);

    @Query("{'eStoreId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    List<ProductEntity> findByStoreAndUser(UUID eStoreId, UUID eUserId, String session);

    @Query("{'eProductId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    Optional<ProductEntity> findByProductAndUser(UUID eProductId, UUID eUserId, String session);

}
