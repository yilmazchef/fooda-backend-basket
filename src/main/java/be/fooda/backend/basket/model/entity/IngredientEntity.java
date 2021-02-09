package be.fooda.backend.basket.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class IngredientEntity {

    @Id
    private String id;

    private String eIngredientId;

    private BigDecimal cost;
}
