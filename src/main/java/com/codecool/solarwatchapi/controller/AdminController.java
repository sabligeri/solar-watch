package com.codecool.solarwatchapi.controller;

import com.codecool.solarwatchapi.model.entity.Role;
import com.codecool.solarwatchapi.model.entity.UserEntity;
import com.codecool.solarwatchapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DeleteMapping("/delete-user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @PostMapping("/add-admin/{userId}")
    public ResponseEntity<?> addAdminRole(@PathVariable Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok("Admin role added successfully");
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok("Everything is working");
    }
}
