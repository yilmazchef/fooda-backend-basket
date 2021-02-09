package be.fooda.backend.basket.model.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeleteRequest {
    private UUID eProductId;
    private UUID eUserId;
    private String session;
}
