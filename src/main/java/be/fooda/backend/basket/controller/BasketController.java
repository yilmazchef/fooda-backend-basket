package be.fooda.backend.basket.controller;

import be.fooda.backend.basket.client.AddressClient;
import be.fooda.backend.basket.client.ContactClient;
import be.fooda.backend.basket.client.UserClient;
import be.fooda.backend.basket.dao.*;
import be.fooda.backend.basket.model.entity.*;
import be.fooda.backend.basket.model.http.HttpFailureMessages;
import be.fooda.backend.basket.model.http.HttpSuccessMessages;
import be.fooda.backend.basket.service.mapper.*;
import be.fooda.backend.basket.model.create.PaymentMethodCreate;
import be.fooda.backend.basket.model.create.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Basket Service")
public class BasketController {

    // Product DI ..
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // Address DI ..
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final AddressClient addressClient;

    // Contact DI ..
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final ContactClient contactClient;

    // Payment DI ..
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    // Order DI ..
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    //Clients
    private final UserClient userClient;

//    PRODUCT END POINTS


    @GetMapping("product/get_product_by_id")
    public ResponseEntity getProductById(@RequestParam String productId) {

        final Optional<ProductEntity> foundProduct = productRepository.findById(productId);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("product/get_product_by_user_and_external_product_id")
    public ResponseEntity getProductByUserAndExternalProductId(@RequestParam UUID eUserId, @RequestParam String session, @RequestParam UUID eProductId) {

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//        if (!productClient.exists(externalProductId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.PRODUCT_DOES_NOT_EXIST);
//        }


        final Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(eProductId, eUserId, session);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundProduct);
    }

    @GetMapping("product/get_products_by_user_and_store")
    public ResponseEntity getProductsByUserAndStore(@RequestParam UUID eUserId, @RequestParam String session, @RequestParam(required = false) UUID eStoreId) {

//        List<FoodaBasketProduct> products;
//
//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//        if (externalStoreId == null){
//            products = basketService.getProductsByUser(externalUserId, userSession);
//            if(products.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.USER_HAS_NO_PRODUCTS);
//        }else{
//            if (!storeClient.exists(externalStoreId)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.STORE_DOES_NOT_EXIST);
//            }
//            products = basketService.getProductsByUserAndStore(externalUserId, userSession, externalStoreId);
//            if(products.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);
//        }
//        return ResponseEntity.status(HttpStatus.FOUND).body(products);

        List<ProductEntity> foundProducts;
        if (eStoreId == null) {
            foundProducts = productRepository.findByUser(eUserId, session);

            if (foundProducts.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS);
        } else {
            foundProducts = productRepository.findByStoreAndUser(eStoreId, eUserId, session);

            if (foundProducts.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(foundProducts);
    }


    @ApiOperation(value = "Adds product to basket")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "PRODUCT_ADDED")
    })
    @PostMapping("product/add_product")
    public ResponseEntity addProduct(@RequestBody @Valid ProductCreate productCreate) {

//        if (!userClient.exists(product.getUser())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.USER_DOES_NOT_EXIST);
//        }
//        if (!storeClient.exists(product.getStore())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.STORE_DOES_NOT_EXIST);
//        }
//        if (productClient.exists(product.getExternalProductId())){
//            if (!inventoryClient.hasQuantity(product.getQuantity())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.PRODUCT_OUT_OF_STOCK);
//            }
//            if (!productClient.getCurrentPrice(product.getExternalProductId()).equals(product.getPrice())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.PRODUCT_CURRENT_PRICE_DOES_NOT_MATCH);
//            }
//            if (!product.getIngredients().isEmpty() && !productClient.ingredientsExist(product.getIngredients())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.INGREDIENTS_DOES_NOT_EXIST);
//            }
//        }else{
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMassages.PRODUCT_DOES_NOT_EXIST);
//        }

        final UUID eUserId = productCreate.getUser().getEUserId();
        final String userSession = productCreate.getUser().getSession();
        final UUID eProductId = productCreate.getEProductId();

//        if (eUserId == null || userSession == null || eProductId == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.REQUIRED_FIELDS_ARE_MISSING_IN_CREATE_REQUEST);
//        }

        if (productRepository.findByProductAndUser(eProductId, eUserId, userSession).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PRODUCT_ALREADY_EXISTS);
        }

        // CLIENT EXIST CHECKS ..

        productRepository.save(productMapper.toEntity(productCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.PRODUCT_ADDED);
    }

    @PatchMapping("product/update_product_quantity")
    public ResponseEntity updateProductQuantity(@RequestParam UUID eProductId, @RequestParam UUID eUserId, @RequestParam String session, @RequestParam @Positive Integer newQuantity) {

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//
//        if (!productClient.exists(externalProductId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//        }else{
//            Optional<FoodaBasketProduct> product = basketService.getProductByUserAndExternalProductId(externalUserId,userSession,externalProductId);
//            if (!product.isPresent()){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_HAS_NO_SUCH_PRODUCT);
//            }
//
//            if (newQuantity <= 0 | product.get().getQuantity() == newQuantity){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.INVALID_PRODUCT_QUANTITY);
//            }
//
//            if (!inventoryClient.hasQuantity(newQuantity)){
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_OUT_OF_STOCK);
//            }
//
//            FoodaBasketProductUpdate productUpdate = new FoodaBasketProductUpdate();
//            product.get().setQuantity(newQuantity);
//            productMapper.fromEntityToUpdate(product.get(),productUpdate);
//
//            if (basketService.updateProduct(product.get().getId(),productUpdate).get().getQuantity().equals(newQuantity)){
//                return ResponseEntity.status(HttpStatus.OK).body(FoodaBasketHttpSuccessMessages.PRODUCT_UPDATED);
//            }
//        }

        Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(eProductId, eUserId, session);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
        }

        ProductEntity productBeingUpdated = foundProduct.get();
        productBeingUpdated.setQuantity(newQuantity);
        productRepository.save(productBeingUpdated);

        Integer updatedQuantity = productRepository.findByProductAndUser(eProductId, eUserId, session).get().getQuantity();
        if (!updatedQuantity.equals(newQuantity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(HttpFailureMessages.PRODUCT_COULD_NOT_BE_UPDATED);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.PRODUCT_UPDATED);
    }

    @PatchMapping("product/update_product_price")
    public ResponseEntity updateProductPrice(@RequestParam UUID eProductId, @RequestParam UUID eUserId, @RequestParam String session, @RequestParam BigDecimal newPrice) {

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//
//        if (!productClient.exists(externalProductId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//        }else{
//            Optional<FoodaBasketProduct> product = basketService.getProductByUserAndExternalProductId(externalUserId,userSession,externalProductId);
//            if (!product.isPresent()){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
//            }
//
//            FoodaBasketProductUpdate productUpdate = new FoodaBasketProductUpdate();
//            product.get().setPrice(newPrice);
//            productMapper.fromEntityToUpdate(product.get(),productUpdate);
//
//            if (basketService.updateProduct(product.get().getId(),productUpdate).isPresent()){
//                return ResponseEntity.status(HttpStatus.OK).body(FoodaBasketHttpSuccessMessages.PRODUCT_UPDATED);
//            }
//        }

        Optional<ProductEntity> foundProduct = productRepository.findByProductAndUser(eProductId, eUserId, session);

        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);

        }

        ProductEntity productBeingUpdated = foundProduct.get();
        productBeingUpdated.setPrice(newPrice);
        productRepository.save(productBeingUpdated);

        BigDecimal updatedPrice = productRepository.findByProductAndUser(eProductId, eUserId, session).get().getPrice();

        if (!updatedPrice.equals(newPrice)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(HttpFailureMessages.PRODUCT_COULD_NOT_BE_UPDATED);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMessages.PRODUCT_UPDATED);
    }

    @DeleteMapping("product/delete_product_by_id")
    public ResponseEntity deleteProductById(@RequestParam String id) {

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//
//        if (!productClient.exists(externalProductId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//        }else{


        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
        } else {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCT_DELETED);
        }
    }

    @DeleteMapping("product/delete_product_by_user_and_external_product_id")
    public ResponseEntity deleteProductByUserAndExternalProductId(@RequestParam UUID eProductId, @RequestParam UUID eUserId, @RequestParam String session) {
//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//
//        if (!productClient.exists(externalProductId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//        }else{


        Optional<ProductEntity> product = productRepository.findByProductAndUser(eProductId, eUserId, session);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
        } else {
            productRepository.deleteById(product.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCT_DELETED);
        }
    }


    @DeleteMapping("product/delete_products")
    public ResponseEntity deleteProducts(@RequestParam(required = false) UUID eStoreId, @RequestParam UUID eUserId, @RequestParam String session) {

        List<ProductEntity> products = new ArrayList<>();

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }
//
        if (eStoreId == null) {
            products = productRepository.findByUser(eUserId, session);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS);
            }
        } else {
//            if (!storeClient.exists(externalStoreId)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.STORE_DOES_NOT_EXIST);
//            }
            products = productRepository.findByStoreAndUser(eStoreId, eUserId, session);
            if (products.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_PRODUCTS_IN_THIS_STORE);
        }

        for (ProductEntity product : products) {
//            if (!productClient.exists(product.getExternalProductId())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//            }

            if (product != null) {
                productRepository.deleteById(product.getId());
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST_IN_BASKET);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCTS_DELETED);
    }

    @GetMapping("product/product_exists")
    public ResponseEntity productExistsById(@RequestParam String id) {

        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.PRODUCT_EXISTS);
    }

    //    ADDRESS END POINTS


    @GetMapping("address/get_address_by_id")
    public ResponseEntity getAddressById(@RequestParam String addressId) {

        final Optional<AddressEntity> foundAddress = addressRepository.findById(addressId);

        if (!foundAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.ADDRESS_DOES_NOT_EXIST_IN_BASKET);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundAddress);
    }


    @GetMapping("address/get_addresses_by_external_user_id")
    public ResponseEntity getAddressesByExternalUserId(@RequestParam UUID externalUserId) {

        final List<AddressEntity> foundAddresses = addressRepository.findByUser(externalUserId);

        if (foundAddresses.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.USER_HAS_NO_ADDRESS);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundAddresses);
    }

    @PostMapping("address/create_address")
    public ResponseEntity createAddress(@RequestBody AddressCreate addressCreate) {
//        if (!userClient.exists(address.getUser().getExternalUserId())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }
//        if (!addressClient.exists(address)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.ADDRESS_DOES_NOT_EXIST);
//        }
//
//        String savedId = basketService.addAddress(address);
//
//        if (basketService.addressExists(savedId)){
//            return ResponseEntity.status(HttpStatus.OK).body(FoodaBasketHttpSuccessMessages.ADDRESS_ADDED);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(FoodaBasketHttpFailureMessages.UNKNOWN_FAILURE);


        final UUID externalAddressId = addressCreate.getEAddressId();
        final UUID externalUserId = addressCreate.getUser().getEUserId();
        final String userSession = addressCreate.getUser().getSession();

        if (externalAddressId == null || externalUserId == null || userSession == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.REQUIRED_FIELDS_ARE_MISSING_IN_CREATE_REQUEST);
        }

        if (!addressRepository.findByAddressAndUser(externalAddressId, externalUserId, userSession).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.ADDRESS_ALREADY_EXISTS);
        }

        // CLIENT EXIST CHECKS ..

        addressRepository.save(addressMapper.toEntity(addressCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.ADDRESS_ADDED);
    }

    @DeleteMapping("address/delete_address_by_id")
    public ResponseEntity deleteAddressById(@RequestParam String id) {

        Optional<AddressEntity> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.ADDRESS_DOES_NOT_EXIST_IN_BASKET);
        } else {
            addressRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.ADDRESS_DELETED);
        }
    }

    @DeleteMapping("address/delete_address")
    public ResponseEntity deleteAddress(@RequestParam UUID externalUserId, @RequestParam String userSession, @RequestParam UUID externalAddressId) {

//        if (!userClient.exists(externalUserId)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }else{
//            FoodaBasketUser user = userClient.getUser(externalUserId);
//            if (!user.getSession().equals(userSession)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.SESSION_DOES_NOT_EXISTS);
//            }
//        }

        Optional<AddressEntity> address = addressRepository.findByAddressAndUser(externalAddressId, externalUserId, userSession);
        if (address.isPresent()) {
            addressRepository.deleteById(address.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.ADDRESS_DELETED);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(HttpFailureMessages.ADDRESS_DOES_NOT_EXIST_IN_BASKET);
        }
    }

    @GetMapping("address/address_exists")
    public ResponseEntity addressExistsById(@RequestParam String id) {

        if (!addressRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.ADDRESS_DOES_NOT_EXIST);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.ADDRESS_EXISTS);
    }

    //    CONTACT END POINTS

    @GetMapping("contact/get_contact_by_id")
    public ResponseEntity getContactById(@RequestParam String contactId) {

        final Optional<ContactEntity> foundContact = contactRepository.findById(contactId);

        if (!foundContact.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.CONTACT_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundContact);
    }

    @GetMapping("contact/get_contacts_by_external_user_id")
    public ResponseEntity getContactsByExternalUserId(@RequestParam UUID eUserId) {

        final List<ContactEntity> foundContacts = contactRepository.findByUser(eUserId);

        if (foundContacts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.USER_HAS_NO_CONTACT);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundContacts);
    }

    @PostMapping("contact/create_contact")
    public ResponseEntity createContact(@RequestBody ContactCreate contactCreate) {
//        if (!userClient.exists(contact.getUser().getExternalUserId())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }
//        if (!contactClient.exists(contact)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.ADDRESS_DOES_NOT_EXIST);
//        }
//
//        String savedId = basketService.addContact(contact);
//
//        if (basketService.contactExists(savedId)){
//            return ResponseEntity.status(HttpStatus.OK).body(FoodaBasketHttpSuccessMessages.CONTACT_ADDED);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(FoodaBasketHttpFailureMessages.UNKNOWN_FAILURE);

        final UUID eContactId = contactCreate.getEContactId();
        final UUID eUserId = contactCreate.getUser().getEUserId();
        final String session = contactCreate.getUser().getSession();

        if (eContactId == null || eUserId == null || session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.REQUIRED_FIELDS_ARE_MISSING_IN_CREATE_REQUEST);
        }

        if (contactRepository.findByContactAndUser(eContactId, eUserId, session).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.CONTACT_ALREADY_EXISTS);
        }


        // CLIENT EXIST CHECKS ..

        contactRepository.save(contactMapper.toEntity(contactCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.CONTACT_ADDED);
    }

    @DeleteMapping("contact/delete_contact_by_id")
    public ResponseEntity deleteContactById(@RequestParam String id) {

        Optional<ContactEntity> contact = contactRepository.findById(id);
        if (!contact.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.CONTACT_DOES_NOT_EXIST_IN_BASKET);
        } else {
            contactRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.CONTACT_DELETED);
        }
    }

    @DeleteMapping("contact/delete_contact")
    public ResponseEntity deleteContact(@RequestParam UUID eUserId, @RequestParam String session, @RequestParam UUID eContactId) {

        if (!userClient.exists(eUserId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_DOES_NOT_EXIST);
        } else {
            UserEntity user = userClient.getUser(eUserId);
            if (!user.getSession().equals(session)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.SESSION_DOES_NOT_EXISTS);
            }
        }

        Optional<ContactEntity> contact = contactRepository.findByContactAndUser(eContactId, eUserId, session);
        if (contact.isPresent()) {
            contactRepository.deleteById(contact.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.CONTACT_DELETED);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(HttpFailureMessages.CONTACT_DOES_NOT_EXIST_IN_BASKET);
        }
    }

    @GetMapping("contact/contact_exists")
    public ResponseEntity contactExistsById(@RequestParam String id) {

        if (!contactRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.CONTACT_DOES_NOT_EXIST);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.CONTACT_EXISTS);
    }

    //    PAYMENT END POINTS

    @GetMapping("payment/get_payment_by_id")
    public ResponseEntity getPaymentById(@RequestParam String paymentId) {

        final Optional<PaymentEntity> foundPayment = paymentRepository.findById(paymentId);

        if (!foundPayment.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PAYMENT_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundPayment);
    }

    @GetMapping("payment/get_payments_by_external_user_id")
    public ResponseEntity getPaymentsByExternalUserId(@RequestParam UUID eUserId) {

        final List<PaymentEntity> foundPayments = paymentRepository.findByUser(eUserId);

        if (foundPayments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.USER_HAS_NO_PAYMENT);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(foundPayments);
    }

    @PostMapping("payment/create_payment")
    public ResponseEntity createPayment(@RequestBody PaymentCreate paymentCreate) {
//        if (!userClient.exists(contact.getUser().getExternalUserId())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//        }
//        if (!contactClient.exists(contact)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.ADDRESS_DOES_NOT_EXIST);
//        }
//
//        String savedId = basketService.addContact(contact);
//
//        if (basketService.contactExists(savedId)){
//            return ResponseEntity.status(HttpStatus.OK).body(FoodaBasketHttpSuccessMessages.CONTACT_ADDED);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(FoodaBasketHttpFailureMessages.UNKNOWN_FAILURE);

        final PaymentMethodCreate paymentMethod = paymentCreate.getMethod();
        final BigDecimal amount = paymentCreate.getAmount();
        final UUID externalUserId = paymentCreate.getUser().getEUserId();
        final String userSession = paymentCreate.getUser().getSession();

        if (paymentMethod == null || amount == null || externalUserId == null || userSession == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.REQUIRED_FIELDS_ARE_MISSING_IN_CREATE_REQUEST);

        if (paymentRepository.existsByMethodAndAmountAndUser_EUserIdAndUser_Session(PaymentMethodEntity.valueOf(paymentMethod.name()), amount, externalUserId, userSession))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.PAYMENT_ALREADY_EXISTS);

        // CLIENT EXIST CHECKS ..

        paymentRepository.save(paymentMapper.toEntity(paymentCreate));
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMessages.PAYMENT_CREATED);
    }

    @DeleteMapping("payment/delete_payment_by_id")
    public ResponseEntity deletePaymentById(@RequestParam String id) {

        Optional<PaymentEntity> payment = paymentRepository.findById(id);
        if (!payment.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PAYMENT_DOES_NOT_EXIST_IN_BASKET);
        } else {
            paymentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PAYMENT_DELETED);
        }
    }

    @GetMapping("payment/payment_exists")
    public ResponseEntity paymentExistsById(@RequestParam String id) {

        if (!paymentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.PAYMENT_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.PAYMENT_EXISTS);
    }

    //    ORDER END POINTS

    @PostMapping("order/create_orders")
    public ResponseEntity createOrders(@RequestBody List<OrderCreate> orders) {

        for (OrderCreate order : orders) {
//            if (!userClient.exists(order.getUser())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.USER_DOES_NOT_EXIST);
//            }
//            if (!storeClient.exists(order.getStore())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.STORE_DOES_NOT_EXIST);
//            }

            final UUID externalUserId = order.getUser().getEUserId();
            final String userSession = order.getUser().getSession();
            final UUID externalStoreId = order.getStore().getEStoreId();

            if (externalUserId == null || userSession == null || externalStoreId == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.REQUIRED_FIELDS_ARE_MISSING_IN_CREATE_REQUEST);
            }

            if (orderRepository.findByStoreAndUser(externalStoreId, externalUserId, userSession).isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(HttpFailureMessages.ORDER_ALREADY_EXISTS);
            }

            // CLIENT EXIST CHECKS ..

            orderRepository.save(orderMapper.toEntity(order));
        }
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.ORDERS_ADDED);
    }

    @DeleteMapping("order/delete_order_by_id")
    public ResponseEntity deleteOrderById(@RequestParam String id) {

        Optional<OrderEntity> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.ORDER_DOES_NOT_EXIST_IN_BASKET);
        } else {
            orderRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.ORDER_DELETED);
        }
    }

    @DeleteMapping("order/delete_orders")
    public ResponseEntity deleteOrders(@RequestParam(required = false) UUID eStoreId, @RequestParam UUID eUserId, @RequestParam String session) {

        List<OrderEntity> orders = new ArrayList<>();

        if (eStoreId == null) {
            orders = orderRepository.findByUser(eUserId, session);
            if (orders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_ORDERS);
            }
        } else {
//            if (!storeClient.exists(externalStoreId)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.STORE_DOES_NOT_EXIST);
//            }
            orders.add(orderRepository.findByStoreAndUser(eStoreId, eUserId, session).get());
            if (orders.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_ORDER_FROM_THIS_STORE);
        }

        for (OrderEntity order : orders) {
//            if (!productClient.exists(product.getExternalProductId())){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FoodaBasketHttpFailureMessages.PRODUCT_DOES_NOT_EXIST);
//            }

            if (order != null) {
                orderRepository.deleteById(order.getId());
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(HttpFailureMessages.ORDER_DOES_NOT_EXIST_IN_BASKET);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(HttpSuccessMessages.PRODUCTS_DELETED);
    }

    @GetMapping("order/order_exists")
    public ResponseEntity orderExistsById(@RequestParam String id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMessages.ORDER_DOES_NOT_EXIST_IN_BASKET);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMessages.ORDER_EXISTS);
    }

    @GetMapping("order/get_orders_by_user_and_store")
    public ResponseEntity getOrdersByUser(@RequestParam UUID eUserId, @RequestParam String session, @RequestParam(required = false) UUID eStoreId) {

        List<OrderEntity> foundOrders = new ArrayList<>();
        if (eStoreId == null) {
            foundOrders = orderRepository.findByUser(eUserId, session);

            if (foundOrders.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_ORDERS);
        } else {
            Optional<OrderEntity> ofoundOrder = orderRepository.findByStoreAndUser(eStoreId, eUserId, session);

            if (!ofoundOrder.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.USER_HAS_NO_ORDER_FROM_THIS_STORE);

            foundOrders.add(ofoundOrder.get());
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(foundOrders);
    }
}
