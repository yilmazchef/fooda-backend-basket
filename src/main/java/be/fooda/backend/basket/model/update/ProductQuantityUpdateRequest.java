package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityUpdateRequest {

    private String eProductId;
    private String eUserId;
    private String session;
    @Positive
    private Integer newQuantity;
}
