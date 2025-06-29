package com.robo.insurances.domain.vehicle;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "create")
public class Vehicle {
    @NonNull
    public RegistrationNumber registrationNumber;
    @NonNull
    public String type;
}