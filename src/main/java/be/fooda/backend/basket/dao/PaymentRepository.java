package be.fooda.backend.basket.dao;

import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.PaymentMethodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
    List<PaymentEntity> findByUser_ExternalUserId(Long externalUserId);

    boolean existsByMethodAndAmountAndUser_ExternalUserIdAndUser_Session(PaymentMethodEntity method, BigDecimal amount, Long externalUserId, String userSession);

}
