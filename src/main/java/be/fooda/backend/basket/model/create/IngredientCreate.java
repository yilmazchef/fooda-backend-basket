package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientCreate {

    private Long eIngredientId;
    private BigDecimal cost;
}
