package be.fooda.backend.basket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethodEntity {

    ON_DELIVERY_CASH("Cash on delivery"),
    ON_DELIVERY_CREDIT_CARD("Credit card on delivery"),
    ON_DELIVERY_DEBIT_CARD("Debit card on delivery"),
    ON_DELIVERY_COUPON("Coupon / Cheque on delivery"),
    ONLINE_CREDIT_CARD("Online Credit card"),
    ONLINE_DEBIT_CARD("Online debit card"),
    ONLINE_DEBIT_CARD_BANCONTACT("Bancontact online with QR code scan"),
    ONLINE_PAYPAL("Online PayPal with an existing account"),
    ONLINE_PAYPAL_GUEST("Online PayPal with an guest account, no registration required"),
    ONLINE_COIN_FOODA("Online with Fooda crypto currency"),
    ONLINE_COIN_BITCOIN("Online with Bitcoin crypto currency"),
    ONLINE_COUPON("Online Coupon / Cheque with code");

    private final String value;

}
