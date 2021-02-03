package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdate {

    private UUID eStoreId;

    private String name;
}
