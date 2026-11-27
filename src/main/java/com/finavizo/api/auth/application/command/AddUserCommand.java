package com.finavizo.api.auth.application.command;

import jakarta.validation.constraints.*;

public class AddUserCommand {
    @NotBlank
    @Size(max = 200)
    public String name;
    @NotBlank
    @Email
    @Size(max = 500)
    public String username;
    @NotBlank
    @Size(min = 8, max = 30)
    public String password;
}