package be.fooda.backend.basket.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactResponse {

    String id;
    UserResponse user;
    String eContactId;
    String title;
    String familyName;
    String firstName;
    String companyName;
    String phone;
    String email;
    Boolean isDelivery;
    Boolean isBilling;
}
