package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductCreate {

    private Long externalProductId;

    private UserCreate user;

    private StoreCreate store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;

    private Set<IngredientCreate> ingredients;

}
