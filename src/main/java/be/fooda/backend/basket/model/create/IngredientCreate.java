package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientCreate {

    private String eIngredientId;

    private BigDecimal cost;
}
