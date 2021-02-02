package be.fooda.backend.basket.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpSuccessMessages {
    PRODUCT_ADDED("Product added to the basket based on the given information"),
    PRODUCT_UPDATED("Product updated based on the new information"),
    PRODUCT_DELETED("Product deleted based on the given information"),
    PRODUCTS_DELETED("Products deleted based on the given information"),
    ADDRESS_ADDED("Address added to the basket based on the given information"),
    CONTACT_ADDED("Contact added to the basket based on the given information"),
    PAYMENTS_ADDED("Payments added to the basket based on the given information"),
    PAYMENT_UPDATED("Payment updated based on the new information"),
    ORDERS_ADDED("Orders added to the basket based on the given information"),
    ADDRESS_DELETED("Address deleted based on the given information"),
    CONTACT_DELETED("Contact deleted based on the given information"),
    PAYMENT_DELETED("Payment with the given information deleted"),
    ORDER_DELETED("Order with the given information deleted"),
    PRODUCT_EXISTS("Given product exists"),
    CONTACT_EXISTS("Given contact exists"),
    PAYMENT_EXISTS("Given payment exists"),
    ORDER_EXISTS("Given order exists"),
    ADDRESS_EXISTS("Given address exists"),
    PAYMENT_CREATED("Given payment created");

    private final String description;
}
