package be.fooda.backend.basket.model.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OrderUpdate {

    private UserUpdate user;

    private StoreUpdate store;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime requiredTime;

    private String note;
}
