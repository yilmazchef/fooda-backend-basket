package be.fooda.backend.basket.service.flow;

import be.fooda.backend.basket.dao.ProductRepository;
import be.fooda.backend.basket.model.dto.ProductResponse;
import be.fooda.backend.basket.model.http.HttpFailureMessages;
import be.fooda.backend.basket.service.exception.ResourceNotFoundException;
import be.fooda.backend.basket.service.mapper.ProductMapper;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.dto.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductFlow {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public void createProduct(CreateProductRequest request){

        // IF(NULL)
        if(Objects.isNull(request)){
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMessages.FAILED_TO_CREATE_PRODUCT.getDescription());
        }

       /* boolean exists = productRepository.existsByNameAndStore_EStoreId(request.getName(), request.getStore().getEStoreId());

        //  IF(PRODUCT_EXISTS)
        if(exists){
            throw new ResourceNotFoundException(HttpFailureMessages.PRODUCT_ALREADY_EXISTS.getDescription());
        }*/

        // MAP_DTO_TO_ENTITY
        ProductEntity entity = productMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        productRepository.save(entity);

        //return productMapper.toResponse(entity);
    }

    public List<ProductResponse> findAll(int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        Page<ProductEntity> pages = productRepository.findAll(pageable);

        return productMapper.toResponses(pages.toList());
    }

    public List<ProductEntity> findAll(){

        return productRepository.findAll();
    }

    public ProductResponse findById(String id){

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET.getDescription()));

        return productMapper.toResponse(productEntity);
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
        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new ResourceNotFoundException("");
        productRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return productRepository.existsById(id);
    }


}
