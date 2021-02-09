package be.fooda.backend.basket.model.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdate {

    private UserUpdate user;

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
