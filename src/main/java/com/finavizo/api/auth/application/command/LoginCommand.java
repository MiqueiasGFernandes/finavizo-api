package com.finavizo.api.auth.application.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class LoginCommand {
    @NotBlank
    @Email
    @Max(500)
    public String username;
    @NotBlank
    @Max(500)
    public String password;
}
