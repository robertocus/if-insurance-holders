package com.robo.insurances.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Features {
    public static final String SHOW_VEHICLE = "showVehicleInfo";
    private boolean includeVehicleInfo;

    public boolean includeVehicleInfo() {
        return includeVehicleInfo;
    }

    public void setIncludeVehicleInfo(@NonNull Boolean value) {
        this.includeVehicleInfo = value;
    }
}