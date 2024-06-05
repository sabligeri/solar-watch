package com.codecool.solarwatchapi;

import com.codecool.solarwatchapi.model.entity.Role;
import com.codecool.solarwatchapi.model.entity.UserEntity;
import com.codecool.solarwatchapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Set;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdminControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp()  {
        //  userRepository.deleteAll(); // elvileg nem kell majd leteszteljük
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setRoles(Set.of(Role.ROLE_ADMIN));
        userRepository.save(admin);

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("password");
        user.setRoles(Set.of(Role.ROLE_USER));
        userRepository.save(user);
    }

    @Test
    @WithMockUser()
    public void deleteUserAsAdmin() throws Exception {
        mockMvc.perform(delete("/admin/delete-user/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully"));
    }
}
