package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceUpdateRequest {

    private String eProductId;
    private String eUserId;
    private String session;
    private BigDecimal newPrice;
}
