package be.fooda.backend.basket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class BasketApp {
    public static void main(String[] args) {
        SpringApplication.run(BasketApp.class, args);
    }

    @Bean
    public RestTemplate restTemplateConfig() {
        return new RestTemplate();
    }
}
