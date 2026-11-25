package com.finavizo.api.auth.domain.ports;

public interface PasswordHasherPort {
    String hash(String password);

    boolean matches(String password, String hash);
}
