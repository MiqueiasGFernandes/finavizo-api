package com.finavizo.api.auth.application.dto;

import java.util.Date;
import java.util.UUID;

public record AddUserOutputDto(UUID id, String name, String email, boolean enabled, Date createdAt){}
