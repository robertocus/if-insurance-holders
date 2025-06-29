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
    // Detta är en så kallad "Service" men då jag ogillar Service klasser så föredrar jag att skapa en specifik logikklass
    // med namn efter vad den gör och utan extra beroenden till andra "service" metoder i samma klass.
    // Detta är domän logik och hör hemma i domänen.
    // Domän klasser har inte beroende till några andra paket. infrastructure, api, config kan alla referera domän.
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
