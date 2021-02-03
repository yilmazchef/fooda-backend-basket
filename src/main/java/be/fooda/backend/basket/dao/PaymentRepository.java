package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.PaymentMethodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {


    @Query("{'user.eUserId' : ?0}")
    List<PaymentEntity> findByUser(UUID eUserId);

    @Query("{'user.eUserId' : ?0}, 'user.session' : ?1}")
    List<PaymentEntity> findByUser(UUID eUserId, String session);

    @Query("{'eAddressId' : ?0}, 'user.eUserId' : ?1, 'user.session' : ?2}")
    List<PaymentEntity> findByAddressAndUser(UUID eAddressId, UUID eUserId, String session);

    boolean existsByMethodAndAmountAndUser_EUserIdAndUser_Session(PaymentMethodEntity method, BigDecimal amount, UUID eUserId, String session);

}
