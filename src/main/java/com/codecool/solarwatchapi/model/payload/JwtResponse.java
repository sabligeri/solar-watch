package com.codecool.solarwatchapi.model.payload;

import java.util.List;

public record JwtResponse(String jwt, String userName, List<String> roles) {
}
