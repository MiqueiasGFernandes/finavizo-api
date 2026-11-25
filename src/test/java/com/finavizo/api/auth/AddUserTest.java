package com.finavizo.api.auth;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
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
            restTestClient.post()
                        .uri("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of(
                                    "name", faker.name().fullName(),
                                    "username", faker.internet().emailAddress(),
                                    "password", faker.credentials().password()))
                        .exchange()
                        .expectStatus()
                        .isCreated()
                        .expectBody()
                        .jsonPath("$.password")
                        .doesNotExist();
      }
}
