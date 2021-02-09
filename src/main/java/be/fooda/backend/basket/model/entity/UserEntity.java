package be.fooda.backend.basket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserEntity {

    @Id
    private String id;

    private String eUserId;

    private String username;

    private String session;
}
