package be.fooda.backend.basket.service;

import be.fooda.backend.basket.model.create.*;
import be.fooda.backend.basket.model.entity.AddressEntity;
import be.fooda.backend.basket.model.entity.ContactEntity;
import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.update.PaymentUpdate;
import be.fooda.backend.basket.model.update.ProductUpdate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BasketService {
    String addProduct(ProductCreate product);

    boolean productExists(String id);

    boolean productExists(ProductCreate productCreate);

    List<ProductEntity> getProductsByUser(UUID externalUserId, String userSession);

    List<ProductEntity> getProductsByUserAndStore(UUID externalUserId, String userSession, UUID externalStoreId);

    Optional<ProductEntity> getProductByUserAndExternalProductId(UUID externalUserId, String userSession, UUID externalProductId);

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

    Optional<AddressEntity> getAddressByIdAndUser(UUID externalUserId, String userSession, UUID externalAddressId);

    Optional<AddressEntity> deleteAddress(String id);

    Optional<ContactEntity> getContactByIdAndUser(UUID externalUserId, String userSession, UUID externalContactId);

    Optional<ContactEntity> deleteContact(String id);
}
