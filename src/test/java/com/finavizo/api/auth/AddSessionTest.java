package com.finavizo.api.auth;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.finavizo.api.auth.domain.entities.UserEntity;
import com.finavizo.fixtures.BaseE2ETest;

public class AddSessionTest extends BaseE2ETest {

        private UserEntity makePreviousUser() {
                var username = faker.internet().emailAddress();
                var password = faker.credentials().password(8, 30, true);

                this.restTestClient
                                .post()
                                .uri("/api/auth/signup")
                                .body(Map.of(
                                                "name", faker.name().fullName(),
                                                "username", username,
                                                "password", password

                                ))
                                .exchange()
                                .expectStatus()
                                .isCreated();
                var credentials = new UserEntity();

                credentials.password = password;
                credentials.username = username;

                return credentials;
        }

        @Test
        void shouldDenyLoginWhenEmailIsIncorrect() {
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

        @Test
        void shouldDenyLoginWhenPasswordIsIncorrect() {
                var username = this.makePreviousUser().username;
                this.restTestClient
                                .post()
                                .uri("/api/auth/login")
                                .body(Map.of(
                                                "username", username,
                                                "password", faker.credentials().password()))
                                .exchange()
                                .expectStatus()
                                .isUnauthorized();

        }

        @Test
        void shouldReturnTheSessionToken() {
                var previousUser = this.makePreviousUser();

                this.restTestClient
                                .post()
                                .uri("/api/auth/login")
                                .body(Map.of(
                                                "username", previousUser.username,
                                                "password", previousUser.password))
                                .exchange()
                                .expectStatus()
                                .isOk()
                                .expectBody()
                                .jsonPath("$.accessToken")
                                .isNotEmpty();
        }
}
