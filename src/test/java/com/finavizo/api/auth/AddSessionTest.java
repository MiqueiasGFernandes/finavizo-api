package com.finavizo.api.auth;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.finavizo.fixtures.BaseE2ETest;

public class AddSessionTest extends BaseE2ETest {
    @Test
    void shouldDenyLogin() {
        this.restTestClient
                .post()
                .uri("/api/auth/login")
                .body(Map.of(
                        "username", this.faker.internet().emailAddress(),
                        "password", this.faker.credentials().password()))
                .exchange()
                .expectStatus()
                .isUnauthorized();

    }
}
