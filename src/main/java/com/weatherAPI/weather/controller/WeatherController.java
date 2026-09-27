package com.weatherAPI.weather.controller;

import com.weatherAPI.weather.dto.WeatherDTO;
import com.weatherAPI.weather.dto.WeatherRequest;
import com.weatherAPI.weather.services.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherDTO getWeather(@RequestBody WeatherRequest weatherRequest) {
        return weatherService.getWeather(weatherRequest.getCity());
    }
}
