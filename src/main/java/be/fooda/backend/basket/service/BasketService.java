package be.fooda.backend.basket.service;

import be.fooda.backend.basket.model.entity.AddressEntity;
import be.fooda.backend.basket.model.entity.ContactEntity;
import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.create.*;
import be.fooda.backend.basket.model.update.PaymentUpdate;
import be.fooda.backend.basket.model.update.ProductUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketService {
    String addProduct(ProductCreate product);

    boolean productExists(String id);

    boolean productExists(ProductCreate productCreate);

    List<ProductEntity> getProductsByUser(Long externalUserId, String userSession);

    List<ProductEntity> getProductsByUserAndStore(Long externalUserId, String userSession, Long externalStoreId);

    Optional<ProductEntity> getProductByUserAndExternalProductId(Long externalUserId, String userSession, Long externalProductId);

    Optional<ProductEntity> updateProduct(String id, ProductUpdate toUpdate);

    Optional<ProductEntity> delete(String id);

    String addAddress(AddressCreate addressCreate);

    String addContact(ContactCreate contactCreate);

    boolean addressExists(AddressCreate addressCreate);

    boolean contactExists(ContactCreate contactCreate);

    boolean addressExists(String id);

    boolean contactExists(String id);

    String addPayment(PaymentCreate payment);

    boolean paymentExists(PaymentCreate payment);

    boolean paymentExists(String savedId);

    Optional<PaymentEntity> updatePayment(String id, PaymentUpdate paymentUpdate);

    boolean orderExists(OrderCreate order);

    String addOrder(OrderCreate order);

    boolean orderExists(String savedId);

    Optional<AddressEntity> getAddressByIdAndUser(Long externalUserId, String userSession, Long externalAddressId);

    Optional<AddressEntity> deleteAddress(String id);

    Optional<ContactEntity> getContactByIdAndUser(Long externalUserId, String userSession, Long externalContactId);

    Optional<ContactEntity> deleteContact(String id);
}
