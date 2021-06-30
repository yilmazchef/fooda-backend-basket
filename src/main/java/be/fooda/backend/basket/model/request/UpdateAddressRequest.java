package be.fooda.backend.basket.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAddressRequest {

    String id;
    CreateUserRequest user;
    String eAddressId;
    String title;
    String postcode;
    String municipality;
    Boolean isDelivery;
    Boolean isBilling;
}
