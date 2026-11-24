package com.finavizo.api.auth.application.dto;

import java.util.Date;
import java.util.UUID;

public record AddUserOutputDto(UUID id, String name, String username, boolean enabled, Date createdAt){}
