package com.robo.insurances.infrastructure.db;

import com.robo.insurances.domain.insurance.HolderId;
import com.robo.insurances.domain.insurance.Insurance;
import com.robo.insurances.domain.insurance.InsuranceRepository;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RepositoryStatic implements InsuranceRepository {

    @Override
    public Optional<Insurance> findByHolder(@NonNull HolderId holderId) {
        return holderId.equals(HolderId.ABC) ? Optional.of(Insurance.ABC) :
                holderId.equals(HolderId.DEF) ? Optional.of(Insurance.DEF) :
                        holderId.equals(HolderId.GHJ) ? Optional.of(Insurance.GHJ) : Optional.empty();
    }
}
