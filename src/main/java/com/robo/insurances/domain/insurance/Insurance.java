package com.robo.insurances.domain.insurance;

import com.robo.insurances.domain.vehicle.RegistrationNumber;
import lombok.NonNull;
import lombok.Value;

@Value
public class Insurance {
    public static final Insurance ABC = new Insurance(HolderId.ABC, null);
    public static final Insurance DEF = new Insurance(HolderId.DEF, null);
    public static final Insurance GHJ = new Insurance(HolderId.GHJ, null);

    @NonNull
    public HolderId holderId;
    public RegistrationNumber vehicleId;

    public boolean haveVehicle() {
        return vehicleId != null;
    }
}
