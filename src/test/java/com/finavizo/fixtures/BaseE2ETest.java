package com.finavizo.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import net.datafaker.Faker;

@SpringBootTest
@AutoConfigureRestTestClient
public class BaseE2ETest {
    @Autowired
    protected RestTestClient restTestClient;

    protected Faker faker = new Faker();

}
