package be.fooda.backend.basket.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@Document
public class ProductEntity {

    @Id
    private String id;

    private Long externalProductId;

    private UserEntity user;

    private StoreEntity store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;

    private Set<IngredientEntity> ingredients;
}
