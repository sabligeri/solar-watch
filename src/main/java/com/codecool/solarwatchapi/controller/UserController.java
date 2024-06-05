package com.codecool.solarwatchapi.controller;

import com.codecool.solarwatchapi.model.entity.Role;
import com.codecool.solarwatchapi.model.entity.UserEntity;
import com.codecool.solarwatchapi.model.payload.JwtResponse;
import com.codecool.solarwatchapi.model.payload.UsernamePasswordDTO;
import com.codecool.solarwatchapi.repository.UserRepository;
import com.codecool.solarwatchapi.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UsernamePasswordDTO signUpRequest) {
       // UserEntity user = new UserEntity(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), Set.of(Role.ROLE_USER));
       // userRepository.createUser(user);
       // a fentiek még a running activity tracker-ből kimásolt kód részlet ami még nem igazi repository-kkal dolgozott
        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.username());
        user.setPassword(encoder.encode(signUpRequest.password()));
        user.setRoles(Set.of(Role.ROLE_USER));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/register-admin")
    public ResponseEntity<Void> createAdmin(@RequestBody UsernamePasswordDTO signUpRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.username());
        user.setPassword(encoder.encode(signUpRequest.password()));
        user.setRoles(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UsernamePasswordDTO loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }


}
