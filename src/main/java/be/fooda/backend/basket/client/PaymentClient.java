package be.fooda.backend.basket.client;


import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.PaymentMethodEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class PaymentClient {
    public List<PaymentEntity> getPayments(UUID eUserId) {

        List<PaymentEntity> payments = new ArrayList<>();

        PaymentEntity payment1 = new PaymentEntity();
        PaymentEntity payment2 = new PaymentEntity();

        payment1.setId("1");
        payment1.setMethod(PaymentMethodEntity.ON_DELIVERY_CREDIT_CARD);

        payment2.setId("2");
        payment2.setMethod(PaymentMethodEntity.ONLINE_CREDIT_CARD);

        payments.add(payment1);
        payments.add(payment2);

        return payments;
    }
}
