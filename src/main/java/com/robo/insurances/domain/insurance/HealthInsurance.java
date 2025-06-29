package com.robo.insurances.domain.insurance;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class HealthInsurance implements Insurance {

    @NonNull
    public HolderId holderId;
    @Builder.Default
    public Integer dollarCostPerMonth = 20;

    @Override
    public Integer monthlyDollarCost() {
        return dollarCostPerMonth;
    }

    @Override
    public HolderId holderId() {
        return holderId;
    }
}
