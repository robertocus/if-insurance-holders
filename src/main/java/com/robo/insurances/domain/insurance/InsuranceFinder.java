package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.Vehicle;
import com.robo.insurances.domain.vehicle.VehicleClient;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class InsuranceFinder {
    @NonNull
    private InsuranceRepository insuranceRepository;
    @NonNull
    private VehicleClient vehicleClient;

    public List<Insurance> findByHolder(HolderId holderId) {
        List<Insurance> insuranceHolder = insuranceRepository.findByHolder(holderId);
        if (insuranceHolder.isPresent() && insuranceHolder.get().haveVehicle()) {
            Optional<Vehicle> vehicle = vehicleClient.find(insuranceHolder.get().getVehicleId());
        }
        return insuranceHolder;
    }
}
