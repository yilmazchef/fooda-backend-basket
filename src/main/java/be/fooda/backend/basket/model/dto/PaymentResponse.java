package be.fooda.backend.basket.model.dto;

import be.fooda.backend.basket.model.entity.PaymentMethodEntity;
import be.fooda.backend.basket.model.entity.PaymentStatusEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {

     String id;
     UserResponse user;
     PaymentMethodEntity method;
     BigDecimal amount;
     PaymentStatusEntity status;
}
