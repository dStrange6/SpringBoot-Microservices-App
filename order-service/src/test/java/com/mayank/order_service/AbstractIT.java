package com.mayank.catalog_service;

import com.mayank.order_service.TestcontainersConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)    // run application on a random available port during testing
public abstract class AbstractIT                 // Abstract Integration Test class
{
    @LocalServerPort     // will bind the port being used to below variable during testing
    int port;

    @BeforeEach
    void setUp()
    {
        System.out.println(">>> AbstractIT setUp running, port = " + port);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;   // we will use restAssured for testing out apis
    }
}
// above is the basic setup we need so we put it in a abstract class itself
