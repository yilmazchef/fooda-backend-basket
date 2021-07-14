package be.fooda.backend.basket.view.client;

import be.fooda.backend.basket.model.dto.CreateIngredientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ProductClient {
    private final RestTemplate restClient;

   private Boolean existById(UUID id){

       // restClient
       return true;
   }
}
