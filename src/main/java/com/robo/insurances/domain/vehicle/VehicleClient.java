package com.robo.insurances.domain.vehicle;

import java.util.Optional;

public interface VehicleClient {
    Optional<Vehicle> find(RegistrationNumber regNo);
}
