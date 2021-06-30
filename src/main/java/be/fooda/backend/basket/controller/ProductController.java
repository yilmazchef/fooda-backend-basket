package be.fooda.backend.basket.controller;

import be.fooda.backend.basket.dao.ProductRepository;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.http.HttpFailureMessages;
import be.fooda.backend.basket.model.http.HttpSuccessMessages;
import be.fooda.backend.basket.model.request.CreateProductRequest;
import be.fooda.backend.basket.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    // Product DI ..
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("all")
    public ResponseEntity getAll() {

        List<ProductEntity> foundProducts = productRepository.findAll();
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {

        final Optional<ProductEntity> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("user/{eUserId}/session/{session}/eProductId/{eProductId}")
    public ResponseEntity getByUserAndExternalProductId(@PathVariable String eUserId, @PathVariable String session, @PathVariable String eProductId) {

        final Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(eProductId, eUserId, session);
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("user/{eUserId}/session/{session}/store/{eStoreId}/")
    public ResponseEntity getByUserAndStore(@PathVariable String eUserId, @PathVariable String session, @PathVariable String eStoreId) {

        List<ProductEntity> foundProducts = productRepository.findByStoreAndUser(eStoreId, eUserId, session);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }


    @GetMapping("user/{eUserId}/session/{session}")
    public ResponseEntity getByUser(@PathVariable String eUserId, @PathVariable String session) {

        List<ProductEntity> foundProducts = productRepository.findByUser(eUserId, session);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid CreateProductRequest productCreate) {

        final String eUserId = productCreate.getUser().getEUserId();
        final String userSession = productCreate.getUser().getSession();
        final String eProductId = productCreate.getEProductId();
        if (productRepository.findByProductAndUser(eProductId, eUserId, userSession).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PRODUCT_ALREADY_EXISTS);

        productRepository.save(productMapper.toEntity(productCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.PRODUCT_ADDED);
    }

    @PostMapping("many")
    public List<ResponseEntity> createProductFromList(@RequestBody @Valid List<CreateProductRequest> productCreateList) {

        List<ResponseEntity> responseEntityList = new ArrayList<>();

        for (CreateProductRequest productCreate : productCreateList) {
            final String eUserId = productCreate.getUser().getEUserId();
            final String userSession = productCreate.getUser().getSession();
            final String eProductId = productCreate.getEProductId();
            if (productRepository.findByProductAndUser(eProductId, eUserId, userSession).isPresent())
                responseEntityList.add(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PRODUCT_ALREADY_EXISTS));

            productRepository.save(productMapper.toEntity(productCreate));
            responseEntityList.add(ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.PRODUCT_ADDED));
        }

        return responseEntityList;
    }

   /* @PatchMapping("quantity")
    public ResponseEntity updateProductQuantity(@RequestBody @Valid ProductQuantityUpdateRequest request) {

        Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(request.getEProductId(), request.getEUserId(), request.getSession());
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);

        ProductEntity productBeingUpdated = foundProduct.get();
        productBeingUpdated.setQuantity(request.getNewQuantity());
        productRepository.save(productBeingUpdated);

        Integer updatedQuantity = foundProduct.get().getQuantity();
        if (!updatedQuantity.equals(request.getNewQuantity())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(HttpFailureMessages.PRODUCT_COULD_NOT_BE_UPDATED);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.PRODUCT_UPDATED);
    }

    @PatchMapping("price")
    public ResponseEntity updateProductPrice(@RequestBody @Valid ProductPriceUpdateRequest request) {

        Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(request.getEProductId(), request.getEUserId(), request.getSession());
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);

        ProductEntity productBeingUpdated = foundProduct.get();
        productBeingUpdated.setPrice(request.getNewPrice());
        productRepository.save(productBeingUpdated);

        BigDecimal updatedPrice = foundProduct.get().getPrice();
        if (!updatedPrice.equals(request.getNewPrice()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_COULD_NOT_BE_UPDATED);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.PRODUCT_UPDATED);
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity deleteProductById(@PathVariable String id) {

        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);

        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCT_DELETED);
    }

   /* @DeleteMapping
    public ResponseEntity deleteProductByUserAndExternalProductId(@RequestBody @Valid ProductDeleteRequest request) {

        Optional<ProductEntity> product = productRepository.findByProductAndUser(request.getEProductId(), request.getEUserId(), request.getSession());
        if (!product.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);

        productRepository.deleteById(product.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCT_DELETED);
    }*/

    @GetMapping("exists/{id}")
    public ResponseEntity productExistsById(@PathVariable String id) {

        if (!productRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.PRODUCT_EXISTS);
    }
}
