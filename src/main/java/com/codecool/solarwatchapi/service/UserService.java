package com.codecool.solarwatchapi.service;

import com.codecool.solarwatchapi.model.entity.Role;
import com.codecool.solarwatchapi.model.entity.UserEntity;
import com.codecool.solarwatchapi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findCurrentUser() {
        UserDetails contextUser = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String username = contextUser.getUsername();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(format("could not find user %s in the repository", username)));

    }

    public void addRoleFor(UserEntity user, Role role) {

        Set<Role> oldRoles = user.getRoles();

        Set<Role> copiedRoles = new HashSet<>(oldRoles);
        copiedRoles.add(role);
        user.setRoles(copiedRoles);
        userRepository.save(user);
    }
}
