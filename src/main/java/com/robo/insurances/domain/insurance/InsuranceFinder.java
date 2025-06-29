package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.Features;
import com.robo.insurances.domain.vehicle.VehicleClient;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

@AllArgsConstructor
public class InsuranceFinder {
    @NonNull
    private InsuranceRepository insuranceRepository;
    @NonNull
    private VehicleClient vehicleClient;
    @NonNull
    private Features features;

    public List<Insurance> findByHolder(HolderId holderId) {
        var insurances = insuranceRepository.findByHolder(holderId);
        if (features.includeVehicleInfo()) {
            var carInsurances = insurances.stream()
                    .filter(CarInsurance.class::isInstance)
                    .toList();
            if (!carInsurances.isEmpty()) {
                var notVehicleInsurances = insurances.stream().filter(not(CarInsurance.class::isInstance)).toList();
                var vehicleInsurancesWithVehicle = carInsurances.stream()
                        .map(insurance -> vehicleClient.find(((CarInsurance) insurance).getVehicleId())
                                .map(vehicle -> (Insurance) CarInsuranceWithVehicleInfo.builder()
                                        .holderId(insurance.holderId())
                                        .vehicle(vehicle)
                                        .build())
                                .orElse(insurance))
                        .toList();
                return Stream.concat(notVehicleInsurances.stream(), vehicleInsurancesWithVehicle.stream()).toList();
            }
        }
        return insurances;
    }
}
