package be.fooda.backend.basket.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ProductEntity {

    @Id
    private String id;

    private UUID eProductId;

    private UserEntity user;

    private StoreEntity store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;

    private Set<IngredientEntity> ingredients = new HashSet<>();

    public void addIngredient(IngredientEntity ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        this.ingredients.remove(ingredient);
    }
}
