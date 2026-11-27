package com.finavizo.api.auth.application.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginCommand {
    @NotBlank
    @Email
    @Size(max = 500)
    public String username;
    @NotBlank
    @Size(max = 500)
    public String password;
}
