package com.robo.insurances.api.controller;

import com.robo.insurances.domain.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsuranceJson {
    private String holderId;
    private String registrationNumber;

    public static InsuranceJson from(Insurance insurance) {
        return new InsuranceJson(insurance.holderId.value);
    }
}
