package be.fooda.backend.basket.view.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class InventoryClient {
    private final RestTemplate restClient;

    public boolean hasQuantity(Integer quantity) {
        return true;
    }
}
