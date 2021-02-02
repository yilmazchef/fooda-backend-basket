package be.fooda.backend.basket.model.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OrderCreate {

    private UserCreate user;

    private StoreCreate store;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @FutureOrPresent
    private LocalTime requiredTime;

    private String note;

}
