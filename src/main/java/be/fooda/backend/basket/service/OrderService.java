package be.fooda.backend.basket.service;

import be.fooda.backend.basket.dao.OrderRepository;
import be.fooda.backend.basket.model.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderEntity> findByUser(String eUserId) {
        return orderRepository.findByUser(eUserId);
    }

    public List<OrderEntity> findByUser(String eUserId, String session){
        return orderRepository.findByUser(eUserId, session);
    }

    public Optional<OrderEntity> findByStoreAndUser(String eStoreId, String eUserId, String session){
        return orderRepository.findByStoreAndUser(eStoreId, eUserId, session);
    }

    public List<OrderEntity> findByStoreAndUser(String eUserId, String eStoreId){
        return orderRepository.findByStoreAndUser(eUserId, eStoreId);
    }
}
