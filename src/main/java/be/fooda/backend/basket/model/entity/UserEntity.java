package be.fooda.backend.basket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserEntity {

    @Id
    private String id;

    private UUID eUserId;

    private String username;

    private String session;
}
