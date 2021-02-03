package be.fooda.backend.basket.service.impl;

import be.fooda.backend.basket.dao.*;
import be.fooda.backend.basket.model.entity.*;
import be.fooda.backend.basket.service.BasketService;
import be.fooda.backend.basket.service.mapper.*;
import be.fooda.backend.basket.model.create.*;
import be.fooda.backend.basket.model.update.PaymentUpdate;
import be.fooda.backend.basket.model.update.ProductUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BasketServiceImpl implements BasketService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public String addProduct(ProductCreate productCreate) {
        final ProductEntity productEntity = productMapper.toEntity(productCreate);
        return productRepository.save(productEntity).getId();
    }

    @Override
    public boolean productExists(String id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean productExists(ProductCreate product) {
        return productRepository.exists(Example.of(productMapper.toEntity(product)));
    }

    @Override
    public List<ProductEntity> getProductsByUser(UUID eUserId, String userSession) {
        return productRepository.findByUser(eUserId, userSession);
    }

    @Override
    public List<ProductEntity> getProductsByUserAndStore(UUID eUserId, String session, UUID eStoreId) {
        return productRepository.findByStoreAndUser(eStoreId, eUserId, session);
    }

    @Override
    public Optional<ProductEntity> getProductByUserAndExternalProductId(UUID eUserId, String session, UUID eProductId) {
        return productRepository.findByProductAndUser(eProductId, eUserId, session);
    }

    @Override
    public Optional<ProductEntity> updateProduct(String id, ProductUpdate toUpdate) {
        final Optional<ProductEntity> foundProduct = productRepository.findById(id);
        return foundProduct.map(product -> edit(toUpdate, product));
    }

    @Override
    public Optional<ProductEntity> delete(String id) {
        final Optional<ProductEntity> foundProduct = productRepository.findById(id);
        foundProduct.ifPresent(payment -> productRepository.deleteById(id));
        return foundProduct;
    }

    @Override
    public boolean addressExists(AddressCreate addressCreate) {
        return addressRepository.exists(Example.of(addressMapper.toEntity(addressCreate)));
    }

    @Override
    public boolean contactExists(ContactCreate contactCreate) {
        return contactRepository.exists(Example.of(contactMapper.toEntity(contactCreate)));
    }

    @Override
    public String addAddress(AddressCreate addressCreate) {
        final AddressEntity addressEntity = addressMapper.toEntity(addressCreate);
        return addressRepository.save(addressEntity).getId();
    }

    @Override
    public String addContact(ContactCreate contactCreate) {
        final ContactEntity contactEntity = contactMapper.toEntity(contactCreate);
        return contactRepository.save(contactEntity).getId();
    }

    @Override
    public boolean addressExists(String id) {
        return addressRepository.existsById(id);
    }

    @Override
    public boolean contactExists(String id) {
        return contactRepository.existsById(id);
    }

    @Override
    public boolean paymentExists(PaymentCreate payment) {
        return paymentRepository.exists(Example.of(paymentMapper.toEntity(payment)));
    }

    private ProductEntity edit(ProductUpdate toUpdate, ProductEntity product) {
        final ProductEntity productToUpdate = productMapper.toEntity(toUpdate, product);
        return productRepository.save(productToUpdate);
    }

    @Override
    public String addPayment(PaymentCreate payment) {
        final PaymentEntity paymentEntity = paymentMapper.toEntity(payment);
        return paymentRepository.save(paymentEntity).getId();
    }

    @Override
    public boolean paymentExists(String id) {
        return paymentRepository.existsById(id);
    }

    @Override
    public Optional<PaymentEntity> updatePayment(String id, PaymentUpdate paymentToUpdate) {
        final Optional<PaymentEntity> foundPayment = paymentRepository.findById(id);
        return foundPayment.map(payment -> edit(paymentToUpdate, payment));
    }

    private PaymentEntity edit(PaymentUpdate toUpdate, PaymentEntity payment) {
        final PaymentEntity paymentToUpdate = paymentMapper.toEntity(toUpdate, payment);
        return paymentRepository.save(paymentToUpdate);
    }

    @Override
    public boolean orderExists(OrderCreate order) {
        return orderRepository.exists(Example.of(orderMapper.toEntity(order)));
    }

    @Override
    public String addOrder(OrderCreate order) {
        final OrderEntity orderEntity = orderMapper.toEntity(order);
        return orderRepository.save(orderEntity).getId();
    }

    @Override
    public boolean orderExists(String id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Optional<AddressEntity> getAddressByIdAndUser(UUID externalUserId, String userSession, UUID externalAddressId) {
        return addressRepository.findByAddressAndUser(externalAddressId, externalUserId, userSession);
    }

    @Override
    public Optional<AddressEntity> deleteAddress(String id) {
        final Optional<AddressEntity> foundAddress = addressRepository.findById(id);
        foundAddress.ifPresent(payment -> addressRepository.deleteById(id));
        return foundAddress;
    }

    @Override
    public Optional<ContactEntity> getContactByIdAndUser(UUID eUserId, String session, UUID eContactId) {
        return contactRepository.findByContactAndUser(eContactId, eUserId, session);
    }

    @Override
    public Optional<ContactEntity> deleteContact(String id) {
        final Optional<ContactEntity> foundContact = contactRepository.findById(id);
        foundContact.ifPresent(payment -> contactRepository.deleteById(id));
        return foundContact;
    }
}
