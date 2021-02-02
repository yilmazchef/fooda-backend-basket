package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductUpdate {

    private Long externalProductId;

    private UserUpdate user;

    private StoreUpdate store;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private Integer quantity;
}
