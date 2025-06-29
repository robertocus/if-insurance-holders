package com.robo.insurances.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robo.insurances.domain.insurance.CarInsurance;
import com.robo.insurances.domain.insurance.CarInsuranceWithVehicleInfo;
import com.robo.insurances.domain.insurance.Insurance;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsuranceJson {
    private String type;
    private String holderId;
    private String vehicleId;
    private VehicleJson vehicle;
    private Integer monthlyDollarCost;

    public static InsuranceJson from(Insurance insurance) {
        InsuranceJson insuranceJson = InsuranceJson.builder()
                .holderId(insurance.holderId().value)
                .monthlyDollarCost(insurance.monthlyDollarCost())
                .type(insurance.type())
                .build();

        if (insurance instanceof CarInsuranceWithVehicleInfo) {
            insuranceJson = insuranceJson.toBuilder().vehicle(VehicleJson.from(((CarInsuranceWithVehicleInfo) insurance).vehicle)).build();
        }
        if (insurance instanceof CarInsurance) {
            insuranceJson = insuranceJson.toBuilder().vehicleId(((CarInsurance) insurance).vehicleId.value).build();
        }
        return insuranceJson;
    }
}
