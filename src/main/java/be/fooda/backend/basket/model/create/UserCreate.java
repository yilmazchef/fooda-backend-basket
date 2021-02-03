package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserCreate {

    private UUID eUserId;

    private String username;

    private String session;
}
