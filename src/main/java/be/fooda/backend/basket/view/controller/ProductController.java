package be.fooda.backend.basket.view.controller;

import be.fooda.backend.basket.model.dto.ProductResponse;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.http.HttpFailureMessages;
import be.fooda.backend.basket.model.http.HttpSuccessMessages;
import be.fooda.backend.basket.model.dto.CreateProductRequest;
import be.fooda.backend.basket.service.flow.ProductFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/basket/product")
@RequiredArgsConstructor
public class ProductController {

    // Product DI ..
    private final ProductFlow productFlow;


    @GetMapping("all")
    public ResponseEntity getAll() {

        List<ProductEntity> foundProducts = productFlow.findAll();
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable String id) {

        /*final Optional<ProductEntity> foundProduct = productFlow.findById(id);
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);*/

        ProductResponse foundProduct = productFlow.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("user/{eUserId}/session/{session}/eProductId/{eProductId}")
    public ResponseEntity getByUserAndExternalProductId(@PathVariable String eUserId, @PathVariable String session, @PathVariable String eProductId) {

        final Optional<ProductEntity> foundProduct = productFlow.findByProductAndUser(eProductId, eUserId, session);
        if (!foundProduct.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("user/{eUserId}/session/{session}/store/{eStoreId}/")
    public ResponseEntity getByUserAndStore(@PathVariable String eUserId, @PathVariable String session, @PathVariable String eStoreId) {

        List<ProductEntity> foundProducts = productFlow.findByStoreAndUser(eStoreId, eUserId, session);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }


    @GetMapping("user/{eUserId}/session/{session}")
    public ResponseEntity getByUser(@PathVariable String eUserId, @PathVariable String session) {

        List<ProductEntity> foundProducts = productFlow.findByUser(eUserId, session);
        if (foundProducts.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid CreateProductRequest productCreate) {

        //if (productFlow.findByProductAndUser(productCreate).isPresent())
          //  return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PRODUCT_ALREADY_EXISTS);
        productFlow.createProduct(productCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.PRODUCT_ADDED);
    }

    @PostMapping("many")
    public List<ResponseEntity> createProductFromList(@RequestBody @Valid List<CreateProductRequest> productCreateList) {

        List<ResponseEntity> responseEntityList = new ArrayList<>();

        for (CreateProductRequest productCreate : productCreateList) {

            if (productFlow.findByProductAndUser(productCreate).isPresent())
                responseEntityList.add(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PRODUCT_ALREADY_EXISTS));

            productFlow.createProduct(productCreate);
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

        /*
        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);*/

        productFlow.deleteById(id);
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

        if (!productFlow.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.PRODUCT_EXISTS);
    }
}
