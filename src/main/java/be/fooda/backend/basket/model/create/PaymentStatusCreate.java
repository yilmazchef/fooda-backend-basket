package be.fooda.backend.basket.model.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatusCreate {
    ALL_PAID ("All amount paid already"),
    PARTIALLY_PAID ("Waiting partial payment"),
    NOT_PAID("Not paid yet"),
    CANCELLED ("Payment cancelled");

    private final String value;
}
