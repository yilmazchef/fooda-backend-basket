package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityUpdateRequest {

    private UUID eProductId;
    private UUID eUserId;
    private String session;
    @Positive
    private Integer newQuantity;
}
