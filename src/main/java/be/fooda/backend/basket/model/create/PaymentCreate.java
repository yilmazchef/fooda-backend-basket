package be.fooda.backend.basket.model.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentCreate {

    private UserCreate user;

    private PaymentMethodCreate method;

    private BigDecimal amount;

    private PaymentStatusCreate status;
}
