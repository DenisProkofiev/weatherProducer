package com.den.weather.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "type_notification")
    private String typeNotification;

    @Column(name = "destination")
    private String destination;

    @Column(name = "duration_weather_forecast")
    private String duration;

    @Column(name = "status")
    private String status;

    @Column(name = "error_discription")
    private String error;



}
