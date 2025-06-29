package com.robo.insurances.domain.vehicle;

import lombok.NonNull;
import lombok.Value;

@Value
public class Vehicle {
    public static final Vehicle ABC = new Vehicle(RegistrationNumber.ABC);
    public static final Vehicle DEF = new Vehicle(RegistrationNumber.DEF);
    public static final Vehicle GHJ = new Vehicle(RegistrationNumber.GHJ);

    @NonNull
    public RegistrationNumber registrationNumber;
}
