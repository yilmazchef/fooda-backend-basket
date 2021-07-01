package be.fooda.backend.basket.service;

import be.fooda.backend.basket.dao.ContactRepository;
import be.fooda.backend.basket.model.entity.ContactEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<ContactEntity> findByUser(String eUserId){
        return contactRepository.findByUser(eUserId);
    }

    public List<ContactEntity> findByUser(String eUserId, String session){
        return contactRepository.findByUser(eUserId, session);
    }

    public Optional<ContactEntity> findByContactAndUser(String eContactId, String eUserId, String session){
        return contactRepository.findByContactAndUser(eContactId, eUserId, session);
    }
}
