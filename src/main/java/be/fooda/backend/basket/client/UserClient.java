package be.fooda.backend.basket.client;

import be.fooda.backend.basket.model.create.UserCreate;
import be.fooda.backend.basket.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserClient {

    private final RestTemplate restClient;

    private static final String USER_API_URL = "http://192.168.1.31:8041/user/";

    public boolean exists(UserCreate user) {
        return user.getUsername().equalsIgnoreCase("0032467711709");
    }

    public boolean exists(UUID externalUserId) {

        ResponseEntity<String> response = restClient.getForEntity(USER_API_URL + "exists/id/" + externalUserId, String.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

    public boolean exists(String username) {
        return username.equalsIgnoreCase("+32488490509");
    }

    public UserEntity getUser(UUID externalUserId) {
        UserEntity user = new UserEntity();
        user.setEUserId(externalUserId);
        user.setSession("1");
        return user;
    }
}
