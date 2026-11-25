package com.finavizo.api.auth.domain.entities;

import java.util.Date;
import java.util.UUID;

public class UserEntity {
    public UUID id;
    public String name;
    public String username;
    public String password;
    public Date createdAt;
    public boolean enabled;

    public UserEntity() {
        this.enabled = false;
        this.createdAt = new Date();
    }
}