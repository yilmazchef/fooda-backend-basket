package be.fooda.backend.basket.model.create;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactCreate {

    private UserCreate user;

    private String eContactId;

    private String title;

    private String familyName;

    private String firstName;

    private String companyName;

    private String phone;

    private String email;

    private Boolean isDelivery;

    private Boolean isBilling;

}
