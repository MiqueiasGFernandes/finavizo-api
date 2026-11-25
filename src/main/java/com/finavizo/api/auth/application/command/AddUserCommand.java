package com.finavizo.api.auth.application.command;

import jakarta.validation.constraints.*;

public class AddUserCommand {
    @NotBlank
    @Max(200)
    public String name;
    @NotBlank
    @Email
    @Max(500)
    public String username;
    @NotBlank
    @Min(6)
    @Max(12)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\p{P}\\p{S}]).{8,}$", message = "The password must contain: letters, numbers and especial characters")
    public String password;
}