package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreUpdate {

    private Long externalStoreId;

    private String name;
}
