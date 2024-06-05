package com.codecool.solarwatchapi;

import com.codecool.solarwatchapi.model.Coordinate;
import com.codecool.solarwatchapi.model.entity.City;
import com.codecool.solarwatchapi.repository.CityRepository;
import com.codecool.solarwatchapi.service.GeocodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SolarWatchControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeocodingService geocodingService;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    public void setUp() {
        City city = new City();
        city.setName("Budapest");
        city.setLatitude(47.4979);
        city.setLongitude(19.0402);
        cityRepository.save(city);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getSolarEvents() throws Exception {
        when(geocodingService.getCoordinatesForCity("Budapest")).thenReturn(new Coordinate(47.4979, 19.0402));

        mockMvc.perform(get("/solarwatch/Budapest/2024-06-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sunrise").exists())
                .andExpect(jsonPath("$.sunset").exists());
    }
}