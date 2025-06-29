package com.robo.insurances.api.controller;

import com.robo.insurances.domain.NotFoundException;
import com.robo.insurances.domain.insurance.HolderId;
import com.robo.insurances.domain.insurance.InsuranceFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsurancesController {
    @Autowired
    private InsuranceFinder insuranceFinder;

    @GetMapping("/holders/{ssn}")
    public InsuranceJson oneVehicle(@PathVariable String ssn) {
        return insuranceFinder.find(HolderId.parse(ssn))
                .map(InsuranceJson::from)
                .orElseThrow(() -> new NotFoundException("No holder found with id " + ssn));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException() {
        // ...
    }
}
