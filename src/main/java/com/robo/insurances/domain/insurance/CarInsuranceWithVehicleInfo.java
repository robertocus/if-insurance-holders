package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.Vehicle;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CarInsuranceWithVehicleInfo implements Insurance {
    @NonNull
    public HolderId holderId;
    @NonNull
    public Vehicle vehicle;
    @Builder.Default
    public Integer dollarCostPerMonth = 30;

    @Override
    public Integer monthlyDollarCost() {
        return dollarCostPerMonth;
    }

    @Override
    public HolderId holderId() {
        return holderId;
    }

    @Override
    public String type() {
        return "CarInsurance";
    }
}
