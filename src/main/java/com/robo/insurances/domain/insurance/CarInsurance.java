package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.RegistrationNumber;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CarInsurance implements Insurance {
    @NonNull
    public HolderId holderId;
    @NonNull
    public RegistrationNumber vehicleId;
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
}
