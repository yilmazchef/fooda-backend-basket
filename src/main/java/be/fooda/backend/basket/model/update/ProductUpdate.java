package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdate {

    private String eProductId;

    private UserUpdate user;

    private StoreUpdate store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;

    private Set<IngredientUpdate> ingredients;
}
