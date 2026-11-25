package com.finavizo.api.auth.infraestructure.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.finavizo.api.auth.domain.ports.PasswordHasherPort;

@Component
public class PasswordHasherAdapter implements PasswordHasherPort {

    private final PasswordEncoder encoder;

    public PasswordHasherAdapter(
            PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String hash(String password) {
        return this.encoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hash) {
        return this.encoder.matches(password, hash);
    }

}