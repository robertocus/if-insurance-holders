package com.robo.insurances.infrastructure.rest;

import com.robo.insurances.domain.vehicle.RegistrationNumber;
import com.robo.insurances.domain.vehicle.Vehicle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class VehicleClientHttpTest {
    private static ClientAndServer mockServer;
    private VehicleClientHttp vehicleClient;

    @BeforeEach
    void setUpTest() {
        vehicleClient = new VehicleClientHttp("http://localhost:1080");
    }

    @BeforeAll
    static void setUpClass() {
        mockServer = startClientAndServer(1080);
    }

    @AfterAll
    static void tearDownClass() {
        mockServer.stop();
    }

    @Test
    void findVehicle() {
        new MockServerClient("127.0.0.1", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/vehicles/" + RegistrationNumber.ABC.value))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"),
                                        new Header("Cache-Control", "public, max-age=86400"))
                                .withBody("""
                                        {"registrationNumber":"ABC123","type":"Volvo"}
                                        """)
                                .withDelay(TimeUnit.MILLISECONDS, 3));

        assertThat(vehicleClient.find(RegistrationNumber.ABC)).hasValue(Vehicle.create(RegistrationNumber.ABC, "Volvo"));
    }
}