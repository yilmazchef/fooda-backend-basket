package be.fooda.backend.basket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableEurekaClient
@EnableMongoRepositories("be.fooda.backend.basket.dao")
public class BasketApp {
    public static void main(String[] args) {
        SpringApplication.run(BasketApp.class, args);
    }
}
