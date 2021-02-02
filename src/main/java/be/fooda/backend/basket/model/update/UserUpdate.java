package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdate {

    private Long externalUserId;

    private String username;

    private String session;

}
