package be.fooda.backend.basket.model.dto;

import be.fooda.backend.basket.model.entity.StoreEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductRequest {

     String id;
     String eProductId;
     CreateUserRequest user;
     StoreEntity store;
     String name;
     String imageUrl;
     BigDecimal price;
     String description;
     Integer quantity;
     Set<CreateIngredientRequest> ingredients = new HashSet<>();
}
