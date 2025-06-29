package com.robo.insurances.domain.insurance;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class HolderId {
    public static final HolderId HOLDER_1 = HolderId.parse("1");
    public static final HolderId HOLDER_2 = HolderId.parse("2");

    @NonNull
    public String value;

    public static HolderId parse(String no) {
        // validate not null and correct format or throw exception
        return HolderId.of(no);
    }
}
