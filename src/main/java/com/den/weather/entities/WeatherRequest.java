package com.den.weather.entities;

public class WeatherRequest {
    private String typeNotification;
    private String destination;
    private String durationWeatherForecast;

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDurationWeatherForecast() {
        return durationWeatherForecast;
    }

    public void setDurationWeatherForecast(String durationWeatherForecast) {
        this.durationWeatherForecast = durationWeatherForecast;
    }
}


