package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentUpdate {

    private Long externalPaymentId;

    private UserUpdate user;

    private PaymentMethodUpdate method;

    private BigDecimal amount;

    private PaymentStatusUpdate status;
}
