package com.robo.insurances.config;

import com.robo.insurances.domain.insurance.InsuranceFinder;
import com.robo.insurances.domain.insurance.InsuranceRepository;
import com.robo.insurances.domain.vehicle.VehicleClient;
import com.robo.insurances.infrastructure.db.RepositoryStatic;
import com.robo.insurances.infrastructure.rest.VehicleClientHttp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceHolders {
    @Value("service.vehicle.url")
    private String vehicleServiceUrl;

    @Bean
    public InsuranceRepository insuranceHolderRepository() {
        return new RepositoryStatic();
    }

    @Bean
    public VehicleClient vehicleClient() {
        return new VehicleClientHttp(vehicleServiceUrl);
    }

    @Bean
    public InsuranceFinder insuranceHolderFinder(InsuranceRepository insuranceRepository, VehicleClient vehicleClient) {
        return new InsuranceFinder(insuranceRepository, vehicleClient);
    }
}
