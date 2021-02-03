package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductCreate {

    private UUID eProductId;

    private UserCreate user;

    private StoreCreate store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;

    private Set<IngredientCreate> ingredients = new HashSet<>();

    public void addIngredient(IngredientCreate ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientCreate ingredient) {
        this.ingredients.remove(ingredient);
    }
}
