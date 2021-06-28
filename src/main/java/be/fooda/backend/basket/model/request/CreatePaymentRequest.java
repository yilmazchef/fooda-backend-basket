package be.fooda.backend.basket.model.request;

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
public class CreatePaymentRequest {

     String id;
     CreateUserRequest user;
     PaymentMethodEntity method;
     BigDecimal amount;
     PaymentStatusEntity status;
}
