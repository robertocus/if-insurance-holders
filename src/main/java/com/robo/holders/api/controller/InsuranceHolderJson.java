package com.robo.holders.api.controller;

import com.robo.holders.domain.InsuranceHolder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsuranceHolderJson {
    private String registrationNumber;

    public static InsuranceHolderJson from(InsuranceHolder vehicle) {
        return new InsuranceHolderJson(vehicle.getRegistrationNumber().value);
    }
}
