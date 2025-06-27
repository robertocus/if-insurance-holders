package com.robo.holders.config;

import com.robo.holders.domain.InsuranceHolderFinder;
import com.robo.holders.domain.InsuranceHolderRepository;
import com.robo.holders.domain.VehicleClient;
import com.robo.holders.infrastructure.db.HolderRepositoryStatic;
import com.robo.holders.infrastructure.rest.VehicleClientHttp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceHolders {
    @Value("service.vehicle.url")
    private String vehicleServiceUrl;

    @Bean
    public InsuranceHolderRepository insuranceHolderRepository() {
        return new HolderRepositoryStatic();
    }

    @Bean
    public VehicleClient vehicleClient() {
        return new VehicleClientHttp(vehicleServiceUrl);
    }

    @Bean
    public InsuranceHolderFinder insuranceHolderFinder(InsuranceHolderRepository insuranceHolderRepository, VehicleClient vehicleClient) {
        return new InsuranceHolderFinder(insuranceHolderRepository, vehicleClient);
    }
}
