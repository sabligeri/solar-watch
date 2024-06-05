package com.codecool.solarwatchapi.repository;

import com.codecool.solarwatchapi.model.entity.SunDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SunDateRepository extends JpaRepository<SunDate, Long> {
    Optional<SunDate> findByCityIdAndDate(long id, LocalDate queryDate);
}
