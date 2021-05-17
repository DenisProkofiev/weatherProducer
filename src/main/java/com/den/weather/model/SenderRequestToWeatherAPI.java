package com.den.weather.model;

import com.den.weather.config.WeatherConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class SenderRequestToWeatherAPI {
    private final WeatherConfig weatherConfig;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SenderRequestToWeatherAPI(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public ResponseEntity<String> sendRequestToYandexWeather() {
        log.trace("sendRequestToYandexWeather(): - start");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(weatherConfig.getHeadersKey(), weatherConfig.getHeadersValue());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(getURL(), HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        log.trace("sendRequestToYandexWeather(): - finish");
        return responseEntity;
    }

    private String getURL() {
        String sourceUrl = weatherConfig.getSourceUrl();
        String LAT = weatherConfig.getLAT();
        String LON = weatherConfig.getLON();
        return sourceUrl + "?lat=" + LAT + "&lon=" + LON;
    }


}
