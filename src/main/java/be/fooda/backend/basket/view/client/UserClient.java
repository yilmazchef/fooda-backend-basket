package be.fooda.backend.basket.view.client;

import be.fooda.backend.basket.model.entity.UserEntity;
import be.fooda.backend.basket.model.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserClient {

    private final RestTemplate restClient;

    private static final String USER_API_URL = "http://192.168.1.31:8041/user/";

    public boolean existByExternalUserId(CreateUserRequest user) {
        return user.getUsername().equalsIgnoreCase("0032467711709");
    }

    public boolean existByExternalUserId(String externalUserId) {

        ResponseEntity<String> response = restClient.getForEntity(USER_API_URL + "exists/id/" + externalUserId, String.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

    public boolean existsByUsername(String username) {
        return username.equalsIgnoreCase("+32488490509");
    }

    public UserEntity getUser(String externalUserId) {
        UserEntity user = new UserEntity();
        user.setEUserId(externalUserId);
        user.setSession("1");
        return user;
    }
}
