package be.fooda.backend.basket.model.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactUpdate {

    private UserUpdate user;

    private Long externalContactId;

    private String title;

    private String familyName;

    private String firstName;

    private String companyName;

    private String phone;

    private String email;

    private Boolean isDelivery;

    private Boolean isBilling;
}
