package com.finavizo.api.auth.infraestructure.persistence;

import java.util.Date;
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
    public UUID id;

    public String name;
    public String username;

    public boolean enabled;
    public Date createdAt;

    public UserModel(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
