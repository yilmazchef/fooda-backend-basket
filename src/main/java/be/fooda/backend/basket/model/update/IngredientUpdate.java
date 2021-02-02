package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientUpdate {

    private Long eIngredientId;
    private BigDecimal cost;
}
