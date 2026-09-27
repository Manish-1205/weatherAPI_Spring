package com.weatherAPI.weather.services;

import com.weatherAPI.weather.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDTO getWeather(String city) {

        String url = apiUrl
                + "?q=" + city
                + "&appid=" + apiKey
                + "&units=metric";

        //Hitting external api
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        //filtering the response to weather dto
        WeatherDTO weatherDTO = new WeatherDTO();

        weatherDTO.setCity(response.get("name").asText());
        weatherDTO.setTemperature(response.get("main").get("temp").asDouble());
        weatherDTO.setFeelsLike(response.get("main").get("feels_like").asDouble());
        weatherDTO.setHumidity(response.get("main").get("humidity").asInt());
        weatherDTO.setDescription(
                response.get("weather").get(0).get("description").asText()
        );

        return weatherDTO;
    }
}