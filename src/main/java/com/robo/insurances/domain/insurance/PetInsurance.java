package com.robo.insurances.domain.insurance;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PetInsurance implements Insurance {

    @NonNull
    public HolderId holderId;
    @Builder.Default
    public Integer dollarCostPerMonth = 10;

    @Override
    public Integer monthlyDollarCost() {
        return dollarCostPerMonth;
    }

    @Override
    public HolderId holderId() {
        return holderId;
    }
}
