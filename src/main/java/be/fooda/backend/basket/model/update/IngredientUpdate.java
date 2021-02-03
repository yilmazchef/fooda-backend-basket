package be.fooda.backend.basket.model.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientUpdate {

    private UUID eIngredientId;

    private BigDecimal cost;
}
