package com.robo.insurances.domain.insurance;

import java.util.List;

public interface InsuranceRepository {
    List<Insurance> findByHolder(HolderId holderId);
}
