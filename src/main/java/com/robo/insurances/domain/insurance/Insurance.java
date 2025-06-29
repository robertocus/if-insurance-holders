package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.RegistrationNumber;

public interface Insurance {
    static final CarInsurance HOLDER_1_CAR_INSURANCE = CarInsurance.builder().holderId(HolderId.HOLDER_1).vehicleId(RegistrationNumber.ABC).build();
    static final HealthInsurance HOLDER_1_HEALTH_INSURANCE = HealthInsurance.builder().holderId(HolderId.HOLDER_1).build();
    static final PetInsurance HOLDER_1_PET_INSURANCE = PetInsurance.builder().holderId(HolderId.HOLDER_1).build();
    static final CarInsurance HOLDER_2_CAR_INSURANCE = CarInsurance.builder().holderId(HolderId.HOLDER_2).vehicleId(RegistrationNumber.DEF).build();


    Integer monthlyDollarCost();

    HolderId holderId();

    String type();
}
