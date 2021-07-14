package be.fooda.backend.basket.view.controller;


import be.fooda.backend.basket.model.dto.AddressResponse;
import be.fooda.backend.basket.service.flow.AddressFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressFlow addressFlow;

    public ResponseEntity<AddressResponse> findById(){


        return null;
    }
}
