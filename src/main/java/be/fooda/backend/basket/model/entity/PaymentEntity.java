package be.fooda.backend.basket.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document
public class PaymentEntity {

    @Id
    private String id;

    private UserEntity user;

    private PaymentMethodEntity method;

    private BigDecimal amount;

    private PaymentStatusEntity status;
}
