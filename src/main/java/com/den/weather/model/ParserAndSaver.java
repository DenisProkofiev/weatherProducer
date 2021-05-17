package com.den.weather.model;

import com.den.weather.enams.DurationEnum;
import com.den.weather.enams.TypeNotification;
import com.den.weather.entities.RequestEntity;
import com.den.weather.entities.WeatherRequest;
import com.den.weather.entities.WeatherResponse;
import com.den.weather.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ParserAndSaver {
    private final RequestRepository requestRepository;

    public ParserAndSaver(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void entityParsing(ResponseEntity<String> entity, WeatherResponse weatherResponse) {
        log.trace("entityParsing() - start: entityParsingParam = {}", entity, weatherResponse);

        String json = entity.toString().substring(5);
        JSONObject object = new JSONObject(json);
        int temp = object.getJSONObject("fact").getInt("temp");
        String condition = object.getJSONObject("fact").getString("condition");

        weatherResponse.setTemp(temp);
        weatherResponse.setCondition(condition);

        log.trace("entityParsing() - finish");
    }


    public void saveRequest(WeatherRequest weatherRequest, WeatherResponse weatherResponse) {
        log.trace("saveRequest() - start: saveRequestParam = {}", weatherRequest, weatherResponse);

        RequestEntity requestEntity = new RequestEntity();
        JSONObject jsonObject = new JSONObject(weatherRequest);

        String requestStatus = "OK";
        String errorDescription = "";
        String destination = jsonObject.getString("destination");
        String typeNotification = jsonObject.getString("typeNotification");
        String duration = jsonObject.getString("durationWeatherForecast");

        try {
            if (TypeNotification.TypeCheck(typeNotification) != null && DurationEnum.TypeCheck(duration) != null) {
                requestEntity.setTypeNotification(typeNotification);
                requestEntity.setDuration(duration);
            }
        } catch (Exception e) {
            e.printStackTrace();
            requestStatus = "ERROR";
            errorDescription = "notification,or duration not valid, check body of request";
            typeNotification = null;
            duration = null;
        }

        requestEntity.setTypeNotification(typeNotification);
        requestEntity.setDuration(duration);
        requestEntity.setDestination(destination);
        requestEntity.setStatus(requestStatus);
        requestEntity.setError(errorDescription);

        requestRepository.save(requestEntity);

        weatherResponse.setId(requestEntity.getId());
        weatherResponse.setTypeNotification(typeNotification);
        weatherResponse.setDestination(destination);
        weatherResponse.setDuration(duration);
        weatherResponse.setStatus(requestStatus);
        weatherResponse.setError(errorDescription);

        log.trace("saveRequest() - finish");
    }

}
