package com.robo.insurances.domain.insurance;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class HolderId {
    public static final HolderId ABC = HolderId.parse("ABC123");
    public static final HolderId DEF = HolderId.parse("DEF456");
    public static final HolderId GHJ = HolderId.parse("GHJ789");

    @NonNull
    public String value;

    public static HolderId parse(String no) {
        // validate not null and correct format or throw exception
        return HolderId.of(no);
    }
}
