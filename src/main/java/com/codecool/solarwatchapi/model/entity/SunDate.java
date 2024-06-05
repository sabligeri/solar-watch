package com.codecool.solarwatchapi.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SunDate {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String sunset;
    private String sunrise;
    @ManyToOne
    private City city;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
