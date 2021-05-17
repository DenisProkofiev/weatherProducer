package com.den.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherConfig {
    private String sourceUrl;
    private String headersKey;
    private String headersValue;
    private String LAT;
    private String LON;
}
