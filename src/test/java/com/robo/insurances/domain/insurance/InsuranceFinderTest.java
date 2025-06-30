package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.Features;
import com.robo.insurances.domain.vehicle.RegistrationNumber;
import com.robo.insurances.domain.vehicle.Vehicle;
import com.robo.insurances.domain.vehicle.VehicleClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class InsuranceFinderTest {
    private InsuranceFinder insuranceFinder;
    private InsuranceRepository insuranceRepositoryMock;
    private VehicleClient vehicleClientMock;
    private Features features;

    @BeforeEach
    void setUp() {
        insuranceRepositoryMock = mock(InsuranceRepository.class);
        vehicleClientMock = mock(VehicleClient.class);
        features = new Features(false);
        insuranceFinder = new InsuranceFinder(insuranceRepositoryMock, vehicleClientMock, features);
    }

    @Test
    void findByHolderNoFeatureAndNothingFound() {
        when(insuranceRepositoryMock.findByHolder(HolderId.HOLDER_1)).thenReturn(List.of());
        assertThat(insuranceFinder.findByHolder(HolderId.HOLDER_1)).isEqualTo(List.of());
        verify(vehicleClientMock, times(0)).find(any());
    }

    @Test
    void findByHolderNoFeature2Found() {
        when(insuranceRepositoryMock.findByHolder(HolderId.HOLDER_1)).thenReturn(List.of(Insurance.HOLDER_1_CAR_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE));
        assertThat(insuranceFinder.findByHolder(HolderId.HOLDER_1)).isEqualTo(List.of(Insurance.HOLDER_1_CAR_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE));
        verify(vehicleClientMock, times(0)).find(any());
    }

    @Test
    void findByHolderWithFeature2FoundNoVehicleInsurance() {
        features.setIncludeVehicleInfo(true);
        when(insuranceRepositoryMock.findByHolder(HolderId.HOLDER_1)).thenReturn(List.of(Insurance.HOLDER_1_HEALTH_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE));
        assertThat(insuranceFinder.findByHolder(HolderId.HOLDER_1)).isEqualTo(List.of(Insurance.HOLDER_1_HEALTH_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE));
        verify(vehicleClientMock, times(0)).find(any());
    }

    @Test
    void findByHolderWithFeature2FoundWithVehicleInsurance() {
        features.setIncludeVehicleInfo(true);
        var carInsuranceWithVehicle = CarInsuranceWithVehicleInfo.builder().holderId(HolderId.HOLDER_1).vehicle(Vehicle.create(RegistrationNumber.ABC, "Volvo")).build();

        when(insuranceRepositoryMock.findByHolder(HolderId.HOLDER_1)).thenReturn(List.of(Insurance.HOLDER_1_CAR_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE));
        when(vehicleClientMock.find(RegistrationNumber.ABC)).thenReturn(Optional.of(carInsuranceWithVehicle.getVehicle()));

        assertThat(insuranceFinder.findByHolder(HolderId.HOLDER_1)).isEqualTo(List.of(Insurance.HOLDER_1_PET_INSURANCE, carInsuranceWithVehicle));
        verify(vehicleClientMock, times(1)).find(any());
    }
}