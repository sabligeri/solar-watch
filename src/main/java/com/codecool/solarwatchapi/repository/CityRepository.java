package com.codecool.solarwatchapi.repository;

import com.codecool.solarwatchapi.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String city);

    Optional<City> findByLongitudeAndLatitude(double longitude, double latitude);
}
