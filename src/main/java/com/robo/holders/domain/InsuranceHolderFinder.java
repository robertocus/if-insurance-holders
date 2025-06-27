package com.robo.holders.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class InsuranceHolderFinder {
    @NonNull
    private InsuranceHolderRepository insuranceHolderRepository;
    @NonNull
    private VehicleClient vehicleClient;

    public Optional<InsuranceHolder> find(Ssn ssn) {
        Optional<InsuranceHolder> insuranceHolder = insuranceHolderRepository.find(ssn);

    }
}
