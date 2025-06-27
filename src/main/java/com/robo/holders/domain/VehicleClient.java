package com.robo.holders.domain;

import java.util.Optional;

public interface VehicleClient {
    Optional<Vehicle> find(RegistrationNumber regNo);
}
