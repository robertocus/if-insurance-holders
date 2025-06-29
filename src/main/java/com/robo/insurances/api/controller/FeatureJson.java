package com.robo.insurances.api.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class FeatureJson {
    private String name;
    private boolean value;
}
