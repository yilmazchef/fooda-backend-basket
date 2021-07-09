package be.fooda.backend.basket.service.flow;

import be.fooda.backend.basket.dao.AddressRepository;
import be.fooda.backend.basket.model.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressFlow {

    private final AddressRepository addressRepository;

    public List<AddressEntity> findByUser(String eUserId){
        return addressRepository.findByUser(eUserId);
    }


    public List<AddressEntity> findByUser(String eUserId, String session){
        return addressRepository.findByUser(eUserId,session);
    }

    public Optional<AddressEntity> findByAddressAndUser(String eAddressId, String eUserId, String session){
        return addressRepository.findByAddressAndUser(eAddressId,eUserId,session);
    }
}
