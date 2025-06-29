package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.RegistrationNumber;

public interface Insurance {
    static final CarInsurance CAR_INSURANCE = CarInsurance.builder().holderId(HolderId.HOLDER_1).vehicleId(RegistrationNumber.ABC).build();
    static final HealthInsurance HEALTH_INSURANCE = HealthInsurance.builder().holderId(HolderId.HOLDER_1).build();
    static final PetInsurance PET_INSURANCE = PetInsurance.builder().holderId(HolderId.HOLDER_1).build();

    Integer monthlyDollarCost();

    HolderId holderId();
}
