package be.fooda.backend.basket.service;

import be.fooda.backend.basket.dao.ProductRepository;
import be.fooda.backend.basket.model.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> findByUser(String eUserId){
        return productRepository.findByUser(eUserId);
    }

    public List<ProductEntity> findByUser(String eUserId, String session){
        return productRepository.findByUser(eUserId, session);
    }

    public List<ProductEntity> findByStoreAndUser(String eStoreId, String eUserId, String session){
       return productRepository.findByStoreAndUser(eStoreId, eUserId, session);
    }

    Optional<ProductEntity> findByProductAndUser(String eProductId, String eUserId, String session){
        return productRepository.findByProductAndUser(eProductId, eUserId, session);
    }
}
