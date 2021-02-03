package be.fooda.backend.basket.client;

import be.fooda.backend.basket.model.entity.ContactEntity;
import be.fooda.backend.basket.model.create.ContactCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ContactClient {
    public List<ContactEntity> getContacts(Long externalUserId) {
        List<ContactEntity> contacts = new ArrayList<>();

        ContactEntity contactHome = new ContactEntity();
        ContactEntity contactWork = new ContactEntity();

        contactHome.setId("1");
        contactHome.setCompanyName(null);
        contactHome.setEmail("ahmet.ozdemir@student@intecbrussel.be");
        contactHome.setEContactId(UUID.randomUUID());
        contactHome.setFamilyName("Ozdemir");
        contactHome.setFirstName("Ahmet");
        contactHome.setPhone("+32488490509");
        contactHome.setTitle("Home");

        contactWork.setId("2");
        contactWork.setCompanyName("INTEC");
        contactWork.setEmail("ahmet.ozdemir@student@intecbrussel.be");
        contactWork.setEContactId(UUID.randomUUID());
        contactWork.setFamilyName("Ozdemir");
        contactWork.setFirstName("Ahmet");
        contactWork.setPhone("+322345456");
        contactWork.setTitle("Work");

        contacts.add(contactHome);
        contacts.add(contactWork);

        return contacts;
    }

    public boolean exists(ContactCreate billingContact) {
        return true;
    }
}
