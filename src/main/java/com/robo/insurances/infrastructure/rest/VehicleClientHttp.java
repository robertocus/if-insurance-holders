package com.robo.insurances.infrastructure.rest;

import com.robo.insurances.domain.vehicle.RegistrationNumber;
import com.robo.insurances.domain.vehicle.Vehicle;
import com.robo.insurances.domain.vehicle.VehicleClient;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

@Slf4j
public class VehicleClientHttp implements VehicleClient {

    @NonNull
    private WebClient webClient;

    public VehicleClientHttp(@NonNull String vehicleServiceUrl) {
        log.info("Setting up webclient with url " + vehicleServiceUrl);
        this.webClient = WebClient.builder()
                .baseUrl(vehicleServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", vehicleServiceUrl))
                .build();
    }


    @Override
    public Optional<Vehicle> find(@NonNull RegistrationNumber regNo) {
        Mono<VehicleJson> vehicleMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vehicles/{id}")
                        .build(regNo.value))
                .retrieve()
                .bodyToMono(VehicleJson.class);

        // Jag kör block här för att inte spilla in flux/mono i domänen.
        // Skulle jag göra det så skulle jag köra all in på reactive(r2dbc mm) med flux/mono hela vägen ut ur controllern.
        // Har gjort det i ett projekt på SJ och det kan vara intressant men det ställer helt nya krav på utvecklarna och
        // är en större fråga. Bara läsa en stacktrace blir helt annorlunda så även erfarna java utvecklare kan få problem.
        return vehicleMono.blockOptional().map(VehicleJson::toVehicle);
    }

    @Data
    public static class VehicleJson {
        private String registrationNumber;

        public Vehicle toVehicle() {
            return new Vehicle(RegistrationNumber.of(registrationNumber));
        }
    }
}
