package com.robo.holders.domain;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Ssn {
    public static final Ssn ABC = Ssn.parse("ABC123");
    public static final Ssn DEF = Ssn.parse("DEF456");
    public static final Ssn GHJ = Ssn.parse("GHJ789");

    @NonNull
    public String value;

    public static Ssn parse(String no) {
        // validate not null and correct format or throw exception
        return Ssn.of(no);
    }
}
