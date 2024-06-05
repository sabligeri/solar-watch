package com.codecool.solarwatchapi.service;

import com.codecool.solarwatchapi.exception.CityNotFoundException;
import com.codecool.solarwatchapi.model.entity.City;
import com.codecool.solarwatchapi.model.Coordinate;
import com.codecool.solarwatchapi.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate;
    private CityRepository cityRepository;
    private static final Logger logger = LoggerFactory.getLogger(GeocodingService.class);
    @Value("${geocoding.api.key}")
    private String API_KEY;

    @Autowired
    public GeocodingService(RestTemplate restTemplate, CityRepository cityRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public Coordinate getCoordinatesForCity(String city) {
        // Building the URL for the geolocation API
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, API_KEY);


        // Performing the API call
        Coordinate[] response = restTemplate.getForObject(url, Coordinate[].class);
        if (response != null && response.length > 0) {
            logger.info("Coordinates for city {}: {}", city, response[0]);

            // Checking if city already exists in the database and updating if not
            City cityEntity = cityRepository.findByName(city).orElse(null);
            if (cityEntity == null) {
                cityEntity = new City();
                cityEntity.setName(city);
                cityEntity.setLatitude(response[0].lat());
                cityEntity.setLongitude(response[0].lon());
                cityRepository.save(cityEntity);
            }

            return response[0];
        } else {
            logger.error("City not found: {}", city);
            throw new CityNotFoundException("City not found: " + city);
        }
    }
}