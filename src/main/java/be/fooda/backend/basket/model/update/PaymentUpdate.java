package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUpdate {

    private UserUpdate user;

    private PaymentMethodCreate method;

    private BigDecimal amount;

    private PaymentStatusCreate status;
}
