package be.fooda.backend.basket.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Indexed
@Document
public class AddressEntity {

    @Id
    private String id;

    private UserEntity user;

    private String eAddressId;

    private String title;

    private String postcode;

    private String municipality;

    private Boolean isDelivery;

    private Boolean isBilling;

}
