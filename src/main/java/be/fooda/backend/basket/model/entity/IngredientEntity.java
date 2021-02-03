package be.fooda.backend.basket.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class IngredientEntity {

    @Id
    private String id;

    private UUID eIngredientId;

    private BigDecimal cost;
}
