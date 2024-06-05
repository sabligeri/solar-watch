package com.codecool.solarwatchapi.service;

import com.codecool.solarwatchapi.exception.CityNotFoundException;
import com.codecool.solarwatchapi.exception.InvalidDateException;
import com.codecool.solarwatchapi.model.entity.City;
import com.codecool.solarwatchapi.model.SolarEvent;
import com.codecool.solarwatchapi.model.entity.SunDate;
import com.codecool.solarwatchapi.repository.CityRepository;
import com.codecool.solarwatchapi.repository.SunDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class SolarEventService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SolarEventService.class);
    private CityRepository cityRepository;
    private SunDateRepository sunDateRepository;

    @Autowired
    public SolarEventService(RestTemplate restTemplate, CityRepository cityRepository, SunDateRepository sunDateRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
        this.sunDateRepository = sunDateRepository;
    }

    public SolarEvent getSunriseSunsetForCoordinates(double latitude, double longitude, String date) {
        LocalDate localDate = LocalDate.parse(date);
        if (localDate.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Invalid date provided. Date must be in the future.");
        }

        Optional<City> city = cityRepository.findByLongitudeAndLatitude(longitude, latitude);
        if (city.isEmpty()) {
            throw new CityNotFoundException("City not found with the provided coordinates.");
        }

        Optional<SunDate> sunDate = sunDateRepository.findByCityIdAndDate(city.get().getId(), localDate);
        if (sunDate.isPresent()) {
            return new SolarEvent(sunDate.get().getSunrise(), sunDate.get().getSunset());
        }

        // If data is not available in the database, fetch from API and save
        String url = String.format("https://api.sunrise-sunset.org/json?lat=%f&lng=%f&date=%s&formatted=1", latitude, longitude, date);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response != null && "OK".equals(response.get("status"))) {
            Map<String, String> results = (Map<String, String>) response.get("results");
            String sunrise = results.get("sunrise");
            String sunset = results.get("sunset");

            SunDate newSunDate = new SunDate();
            newSunDate.setCity(city.get());
            newSunDate.setDate(localDate);
            newSunDate.setSunrise(sunrise);
            newSunDate.setSunset(sunset);
            sunDateRepository.save(newSunDate);

            logger.info("Fetched from API and saved to database: Sunrise = {}, Sunset = {}", sunrise, sunset);
            return new SolarEvent(sunrise, sunset);
        } else {
            logger.error("Failed to fetch sunrise and sunset times for coordinates: [{}, {}]", latitude, longitude);
            throw new RuntimeException("Unable to fetch sunrise and sunset times.");
        }
    }

    private Optional<City> getCityByLongitudeAndLatitude(double longitude, double latitude) {
        // Implement this method based on your database schema if coordinates are not unique identifiers for cities
        return cityRepository.findByLongitudeAndLatitude(longitude, latitude);
    }
}