package be.fooda.backend.basket.client;

import be.fooda.backend.basket.model.entity.AddressEntity;
import be.fooda.backend.basket.model.create.AddressCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AddressClient {
    public List<AddressEntity> getAddresses(UUID externalUserId) {
        List<AddressEntity> addresses = new ArrayList<>();

        AddressEntity addressHome = new AddressEntity();
        AddressEntity addressWork = new AddressEntity();

        addressHome.setId("1");
        addressHome.setEAddressId(UUID.randomUUID());
        addressHome.setMunicipality("Leuven");
        addressHome.setPostcode("3000");
        addressHome.setTitle("Home");

        addressWork.setId("2");
        addressWork.setEAddressId(UUID.randomUUID());
        addressWork.setMunicipality("Brussel");
        addressWork.setPostcode("1000");
        addressWork.setTitle("Work");

        addresses.add(addressHome);
        addresses.add(addressWork);

        return addresses;
    }

    public boolean exists(AddressCreate billingAddress) {
        return true;
    }

    public boolean exists(Long externalAddressId) {
        return true;
    }
}

