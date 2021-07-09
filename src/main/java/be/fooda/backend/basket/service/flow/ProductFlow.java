package be.fooda.backend.basket.service.flow;

import be.fooda.backend.basket.dao.ProductRepository;
import be.fooda.backend.basket.model.dto.ProductResponse;
import be.fooda.backend.basket.service.exception.ResourceNotFoundException;
import be.fooda.backend.basket.service.mapper.ProductMapper;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.dto.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductFlow {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Optional<ProductEntity> findById(String id){
        return productRepository.findById(id);
    }

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }

    public ProductResponse createProduct(CreateProductRequest request){

        if(Objects.isNull(request)){
            throw new NullPointerException("There is no request in the body");
        }

        boolean exists = productRepository.existsByNameAndStore_EStoreId(request.getName(), request.getStore().getEStoreId());

        if(exists){
            throw new ResourceNotFoundException("Product NOT found");
        }
        ProductEntity entity = productRepository.save(productMapper.toEntity(request));

        return productMapper.toResponse(entity);
    }
    public List<ProductEntity> findByUser(String eUserId){
        return productRepository.findByUser(eUserId);
    }

    public List<ProductEntity> findByUser(String eUserId, String session){
        return productRepository.findByUser(eUserId, session);
    }

    public List<ProductEntity> findByStoreAndUser(String eStoreId, String eUserId, String session){
       return productRepository.findByStoreAndUser(eStoreId, eUserId, session);
    }

    public Optional<ProductEntity> findByProductAndUser(CreateProductRequest productCreate){
        final String eUserId = productCreate.getUser().getEUserId();
        final String session = productCreate.getUser().getSession();
        final String eProductId = productCreate.getEProductId();
        return productRepository.findByProductAndUser(eProductId, eUserId, session);
    }

    public Optional<ProductEntity> findByProductAndUser(String eProductId, String eUserId, String session ){

        return productRepository.findByProductAndUser(eProductId, eUserId, session);
    }

    public void deleteById(String id){
        productRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return productRepository.existsById(id);
    }


}
