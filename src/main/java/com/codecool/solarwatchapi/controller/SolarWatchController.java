package com.codecool.solarwatchapi.controller;


import com.codecool.solarwatchapi.model.SolarEvent;
import com.codecool.solarwatchapi.service.GeocodingService;
import com.codecool.solarwatchapi.service.SolarEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/solarwatch")
public class SolarWatchController {

    private final GeocodingService geocodingService;
    private final SolarEventService solarEventService;

    @Autowired
    public SolarWatchController(GeocodingService geocodingService, SolarEventService solarEventService) {
        this.geocodingService = geocodingService;
        this.solarEventService = solarEventService;
    }

    @GetMapping("/{city}/{date}")
    public ResponseEntity<?> getSolarEvents(@PathVariable String city, @PathVariable String date) {
        try {
            var coordinates = geocodingService.getCoordinatesForCity(city);
            SolarEvent solarEvent = solarEventService.getSunriseSunsetForCoordinates(coordinates.lat(), coordinates.lon(), date);
            return ResponseEntity.ok(solarEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}