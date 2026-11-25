package com.finavizo.api.auth.domain.entities;

import java.util.Date;
import java.util.UUID;

public class UserEntity {
    public UUID id;
    public String name;
    public String username;
    private Date createdAt;
    private boolean enabled;

    public UserEntity() {
        this.enabled = false;
        this.createdAt = new Date();
    }
}