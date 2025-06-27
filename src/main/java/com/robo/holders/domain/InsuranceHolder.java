package com.robo.holders.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class InsuranceHolder {
    public static final InsuranceHolder ABC = new InsuranceHolder(Ssn.ABC);
    public static final InsuranceHolder DEF = new InsuranceHolder(Ssn.DEF);
    public static final InsuranceHolder GHJ = new InsuranceHolder(Ssn.GHJ);

    @NonNull
    public Ssn registrationNumber;
    public RegistrationNumber vehicleId;

    public boolean haveVehicle() {
        return vehicleId != null;
    }
}
