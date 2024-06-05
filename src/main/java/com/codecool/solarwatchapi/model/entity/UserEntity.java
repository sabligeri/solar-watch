package com.codecool.solarwatchapi.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "userEntity_id")) // Ez létrehoz egy külön táblát a role-oknak
    @Enumerated(EnumType.STRING) // Ez tárolja az enum értékeket mint String
    private Set<Role> roles;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
