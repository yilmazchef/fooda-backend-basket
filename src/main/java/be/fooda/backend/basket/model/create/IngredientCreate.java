package be.fooda.backend.basket.model.create;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class IngredientCreate {

    private String eIngredientId;

    private BigDecimal cost;
}
