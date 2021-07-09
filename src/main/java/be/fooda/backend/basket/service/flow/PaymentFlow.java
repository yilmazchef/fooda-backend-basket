package be.fooda.backend.basket.service.flow;

import be.fooda.backend.basket.dao.PaymentRepository;
import be.fooda.backend.basket.model.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentFlow {

    private final PaymentRepository paymentRepository;

    List<PaymentEntity> findByUser(String eUserId){
        return paymentRepository.findByUser(eUserId);
    }

    List<PaymentEntity> findByUser(String eUserId, String session){
        return paymentRepository.findByUser(eUserId, session);
    }

    List<PaymentEntity> findByAddressAndUser(String eAddressId, String eUserId, String session){
        return paymentRepository.findByAddressAndUser(eAddressId, eUserId, session);
    }
}
