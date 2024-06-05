package com.codecool.solarwatchapi;

import com.codecool.solarwatchapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void registerUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newuser\", \"password\": \"password\"}"))
                .andExpect(status().isCreated());

        assert (userRepository.findUserByUsername("newuser").isPresent());
    }

    @Test
    public void registerAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register-admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newadmin\", \"password\": \"password\"}"))
                .andExpect(status().isCreated());

        assert (userRepository.findUserByUsername("newadmin").isPresent());
    }

    @Test
    public void loginUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newuser\", \"password\": \"password\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/user/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newuser\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists());
    }
}
