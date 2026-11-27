package com.finavizo.api.auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.finavizo.fixtures.BaseE2ETest;

@SpringBootTest
@AutoConfigureRestTestClient
public class AddUserTest extends BaseE2ETest {
      @Test
      void shouldCreateUser() {
            String password = faker.credentials().password();
            restTestClient.post()
                        .uri("/api/v1/auth/signup")
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

      // @Test
      // void shouldRejectStronglessPasswordsWithOnlyNumbers() {
      // this.restTestClient
      // .post()
      // .uri("/api/v1/auth/signup")
      // .body(Map.of(
      // "name", faker.name().fullName(),
      // "username", faker.internet().emailAddress(),
      // "password", "123456"))
      // .exchange()
      // .expectStatus()
      // .isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT)
      // .expectBody()
      // .jsonPath("$.detail")
      // .isEqualTo("The password must contain: letters, numbers and especial
      // characters.");
      // }

      // @Test
      // void shouldRejectStronglessPasswordsWithOnlyLetters() {
      // this.restTestClient
      // .post()
      // .uri("/api/v1/auth/signup")
      // .body(Map.of(
      // "name", faker.name().fullName(),
      // "username", faker.internet().emailAddress(),
      // "password", "casaldia"))
      // .exchange()
      // .expectStatus()
      // .isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT)
      // .expectBody()
      // .jsonPath("$.detail")
      // .isEqualTo("The password must contain: letters, numbers and especial
      // characters.");
      // }

      @Test
      void shouldReturnConflit() {
            String duplicatedUsername = faker.internet().emailAddress();

            restTestClient
                        .post()
                        .uri("/api/v1/auth/signup")
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", duplicatedUsername,
                                    "password", faker.credentials().password()))
                        .exchange();

            restTestClient
                        .post()
                        .uri("/api/v1/auth/signup")
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", duplicatedUsername,
                                    "password", faker.credentials().password()))
                        .exchange()
                        .expectStatus()
                        .isEqualTo(HttpStatus.CONFLICT);
      }

}
