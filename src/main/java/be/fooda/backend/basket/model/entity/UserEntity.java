package be.fooda.backend.basket.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class UserEntity {

    @Id
    private String id;

    private Long externalUserId;

    private String username;

    private String session;
}
