package be.fooda.backend.basket.client;

import be.fooda.backend.basket.model.create.StoreCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class StoreClient {
    private final RestTemplate restClient;

    public boolean exists(StoreCreate store) {
        return true;
    }

    public boolean exists(Long externalStoreId) {
        return true;
    }
}
