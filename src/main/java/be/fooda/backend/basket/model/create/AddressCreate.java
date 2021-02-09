package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressCreate {

    private UserCreate user;

    private String eAddressId;

    private String title;

    private String postcode;

    private String municipality;

    private Boolean isDelivery;

    private Boolean isBilling;

}
