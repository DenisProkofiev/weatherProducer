package com.den.weather.entities;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Data
@Component
public class WeatherResponse {
    private Long id;
    private LocalDateTime dateTime = LocalDateTime.now();
    private int temp;
    private String condition;
    private String typeNotification;
    private String destination;
    private String duration;
    private String status;
    private String error;

    public WeatherResponse() {
    }

}
