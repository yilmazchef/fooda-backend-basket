package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdate {

    private UserUpdate user;

    private UUID eAddressId;

    private String title;

    private String postcode;

    private String municipality;

    private Boolean isDelivery;

    private Boolean isBilling;

}
