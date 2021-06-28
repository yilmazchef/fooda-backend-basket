package be.fooda.backend.basket.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalTime;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    String id;
    UserResponse user;
    StoreEntity store;
    LocalTime requiredTime;
    String note;
}
