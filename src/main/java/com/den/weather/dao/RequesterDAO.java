package com.den.weather.dao;

import com.den.weather.config.WeatherConfig;
import com.den.weather.entities.RequestEntity;
import com.den.weather.entities.WeatherRequest;
import com.den.weather.entities.WeatherResponse;
import com.den.weather.model.ParserAndSaver;
import com.den.weather.model.SenderRequestToWeatherAPI;
import com.den.weather.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequesterDAO {
    private final RequestRepository requestRepository;
    private final SenderRequestToWeatherAPI sender;
    private final ParserAndSaver parserAndSaver;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RequesterDAO(RequestRepository requestRepository, WeatherConfig weatherConfig, WeatherResponse weatherResponse, SenderRequestToWeatherAPI sender, ParserAndSaver parserAndSaver) {
        this.requestRepository = requestRepository;
        this.sender = sender;
        this.parserAndSaver = parserAndSaver;
    }

    public WeatherResponse handlingRequest(WeatherRequest weatherRequest) {
        log.trace("handlingRequest() - start: handlingRequestParam = {}", weatherRequest);

        WeatherResponse weatherResponse = new WeatherResponse();
        ResponseEntity<String> entity = sender.sendRequestToYandexWeather();

        parserAndSaver.entityParsing(entity, weatherResponse);
        parserAndSaver.saveRequest(weatherRequest, weatherResponse);

        log.trace("handlingRequest() - finish");
        return weatherResponse;
    }


    public List<RequestEntity> getRequests() {
        return requestRepository.findAll();
    }

}
