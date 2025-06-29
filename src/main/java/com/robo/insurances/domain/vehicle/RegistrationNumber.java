package com.robo.insurances.domain.vehicle;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class RegistrationNumber {
    public static final RegistrationNumber ABC = RegistrationNumber.parse("ABC123");
    public static final RegistrationNumber DEF = RegistrationNumber.parse("DEF456");

    @NonNull
    public String value;

    public static RegistrationNumber parse(String no) {
        // validate correct format or throw exception
        return RegistrationNumber.of(no);
    }
}
