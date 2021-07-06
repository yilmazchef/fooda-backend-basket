package be.fooda.backend.basket.model.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StoreResponse {

    String id;
    String eStoreId;
    String name;
}
