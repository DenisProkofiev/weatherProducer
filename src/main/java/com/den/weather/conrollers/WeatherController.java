package com.den.weather.conrollers;

import com.den.weather.dao.RequesterDAO;
import com.den.weather.entities.RequestEntity;
import com.den.weather.entities.WeatherRequest;
import com.den.weather.entities.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class WeatherController {
    private final RequesterDAO requesterDAO;
    private final KafkaTemplate<String, String>kafkaTemplate;
    private final ObjectMapper objectMapper;

    public WeatherController(RequesterDAO requesterDAO, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.requesterDAO = requesterDAO;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PutMapping("/sendWeatherForecast")
    public String getWeather(@RequestBody WeatherRequest weatherRequest) {
        log.trace("getWeather() - start: getWeatherParam = {}", weatherRequest);
        WeatherResponse weatherResponse= requesterDAO.handlingRequest(weatherRequest);
        sendOrder(weatherResponse);
        log.trace("getWeather() - final");
        return String.format("Weather is: %s  Temperature is: %d",weatherResponse.getCondition(), weatherResponse.getTemp());
    }

    @GetMapping("/getRequest")
    public List<RequestEntity> getRequest() {
        return requesterDAO.getRequests();
    }


    public void sendOrder (WeatherResponse data) {
        log.trace("sendOrder() - start: sendOrderParam = {}", data);
        try {
            String s = objectMapper.writeValueAsString(data);
            kafkaTemplate.send("weatherForecast", s);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        log.trace("sendOrder() - finish");
    }

}

