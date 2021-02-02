package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreCreate {

    private Long externalStoreId;

    private String name;
}
