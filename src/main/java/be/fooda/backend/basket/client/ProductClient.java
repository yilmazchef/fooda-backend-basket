package be.fooda.backend.basket.client;

import be.fooda.backend.basket.model.request.CreateIngredientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class ProductClient {
    private final RestTemplate restClient;

    public BigDecimal getCurrentPrice(Long externalProductId) {

        return new BigDecimal(10);
    }

    public boolean exists(Long externalProductId) {
        return true;
    }

    public boolean ingredientsExist (Set<CreateIngredientRequest> ingredients){
        return true;
    }
}
