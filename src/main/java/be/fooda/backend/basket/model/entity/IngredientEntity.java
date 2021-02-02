package be.fooda.backend.basket.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document
public class IngredientEntity {
    @Id
    private String id;

    private Long eIngredientId;

    private BigDecimal cost;
}
