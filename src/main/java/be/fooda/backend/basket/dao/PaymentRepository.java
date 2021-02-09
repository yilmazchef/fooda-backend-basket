package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.PaymentMethodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

    @Query("{'user.eUserId' : ?0}")
    List<PaymentEntity> findByUser(String eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<PaymentEntity> findByUser(String eUserId, String session);

    @Query("{'eAddressId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    List<PaymentEntity> findByAddressAndUser(String eAddressId, String eUserId, String session);

}
