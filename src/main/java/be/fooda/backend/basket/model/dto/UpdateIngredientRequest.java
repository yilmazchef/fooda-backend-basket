package be.fooda.backend.basket.model.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateIngredientRequest {

    String id;
    String eIngredientId;
    BigDecimal cost;
}
