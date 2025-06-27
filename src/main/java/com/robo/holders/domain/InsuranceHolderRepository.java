package com.robo.holders.domain;

import java.util.Optional;

public interface InsuranceHolderRepository {
    Optional<InsuranceHolder> find(Ssn registrationNumber);
}
