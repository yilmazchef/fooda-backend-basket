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
