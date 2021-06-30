package be.fooda.backend.basket.model.request;

import be.fooda.backend.basket.model.entity.StoreEntity;
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
public class UpdateOrderRequest {

    String id;
    CreateUserRequest user;
    StoreEntity store;
    LocalTime requiredTime;
    String note;
}
