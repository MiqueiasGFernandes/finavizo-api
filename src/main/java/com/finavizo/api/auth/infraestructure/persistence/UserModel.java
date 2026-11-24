package com.finavizo.api.auth.infraestructure.persistence;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public String name;
    public String username;

    public UserModel(String name, String email) {
        this.name = name;
        this.username = email;
    }
}
