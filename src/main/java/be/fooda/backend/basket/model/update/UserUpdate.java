package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

    private UUID eUserId;

    private String username;

    private String session;
}
