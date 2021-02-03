package be.fooda.backend.basket.model.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ContactCreate {

    private UserCreate user;

    private UUID eContactId;

    private String title;

    private String familyName;

    private String firstName;

    private String companyName;

    private String phone;

    private String email;

    private Boolean isDelivery;

    private Boolean isBilling;

}
