package com.codecool.solarwatchapi.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    @OneToMany(mappedBy = "city")
    private Set<SunDate> sunDates;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Set<SunDate> getSunDates() {
        return sunDates;
    }

    public void setSunDates(Set<SunDate> sunDates) {
        this.sunDates = sunDates;
    }
}
