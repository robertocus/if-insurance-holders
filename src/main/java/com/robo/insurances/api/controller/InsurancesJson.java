package com.robo.insurances.api.controller;

import com.robo.insurances.domain.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InsurancesJson {
    private List<InsuranceJson> insurancesJson;

    public static InsurancesJson from(List<Insurance> insurances) {
        return new InsurancesJson(insurances.stream().map(InsuranceJson::from).toList());
    }
}
