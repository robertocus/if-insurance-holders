package com.robo.insurances.api.controller;

import com.robo.insurances.domain.Features;
import com.robo.insurances.domain.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeaturesController {
    @Autowired
    private Features features;

    @GetMapping("/features/{featureName}")
    public FeatureJson showFeature(@PathVariable String featureName) {
        if (Features.SHOW_VEHICLE.equals(featureName)) {
            return FeatureJson.create(Features.SHOW_VEHICLE, features.includeVehicleInfo());
        }
        throw new NotFoundException("Cannot find feature by name " + featureName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/features/{featureName}")
    public FeatureJson updateFeature(@PathVariable String featureName, @RequestParam("toggleValue") String toggleValue) {
        if (Features.SHOW_VEHICLE.equals(featureName)) {
            features.setIncludeVehicleInfo(Boolean.valueOf(toggleValue));
        }
        return showFeature(featureName);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException() {
        // ...
    }
}
