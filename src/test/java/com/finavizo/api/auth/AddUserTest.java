package com.finavizo.api.auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import net.datafaker.Faker;

@SpringBootTest
@AutoConfigureRestTestClient
public class AddUserTest {
      @Autowired
      private RestTestClient restTestClient;
      private Faker faker = new Faker();

      @Test
      void shouldCreateUser() {
            String password = faker.credentials().password();
            restTestClient.post()
                        .uri("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", faker.internet().emailAddress(),
                                    "password", password))
                        .exchange()
                        .expectStatus()
                        .isCreated()
                        .expectBody()
                        .jsonPath("$.password")
                        .value(value -> assertThat(value).isNotEqualTo(password));

      }

      @Test
      void shouldReturnConflit() {
            String duplicatedUsername = faker.internet().emailAddress();

            restTestClient
                        .post()
                        .uri("/api/auth/signup")
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", duplicatedUsername,
                                    "password", faker.credentials().password()))
                        .exchange();

            restTestClient
                        .post()
                        .uri("/api/auth/signup")
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", duplicatedUsername,
                                    "password", faker.credentials().password()))
                        .exchange()
                        .expectStatus()
                        .isEqualTo(HttpStatus.CONFLICT);
      }
}
