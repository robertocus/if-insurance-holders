package com.robo.insurances.infrastructure.db;

import com.robo.insurances.domain.insurance.HolderId;
import com.robo.insurances.domain.insurance.Insurance;
import com.robo.insurances.domain.insurance.InsuranceRepository;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class InsuranceRepositoryStatic implements InsuranceRepository {
    private Map<HolderId, List<Insurance>> data = Map.of(
            HolderId.HOLDER_1, List.of(Insurance.HOLDER_1_HEALTH_INSURANCE, Insurance.HOLDER_1_PET_INSURANCE, Insurance.HOLDER_1_CAR_INSURANCE),
            HolderId.HOLDER_2, List.of(Insurance.HOLDER_2_CAR_INSURANCE)
    );

    @Override
    public List<Insurance> findByHolder(@NonNull HolderId holderId) {
        return data.get(holderId);
    }
}