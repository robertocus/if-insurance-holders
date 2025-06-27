package com.robo.holders.infrastructure.db;

import com.robo.holders.domain.InsuranceHolder;
import com.robo.holders.domain.InsuranceHolderRepository;
import com.robo.holders.domain.Ssn;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HolderRepositoryStatic implements InsuranceHolderRepository {

    @Override
    public Optional<InsuranceHolder> find(@NonNull Ssn ssn) {
        return ssn.equals(Ssn.ABC) ? Optional.of(InsuranceHolder.ABC) :
                ssn.equals(Ssn.DEF) ? Optional.of(InsuranceHolder.DEF) :
                        ssn.equals(Ssn.GHJ) ? Optional.of(InsuranceHolder.GHJ) : Optional.empty();
    }
}
