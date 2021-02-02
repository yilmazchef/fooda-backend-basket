package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreate {

    private Long externalUserId;

    private String username;

    private String session;

}
