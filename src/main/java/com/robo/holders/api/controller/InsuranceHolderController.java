package com.robo.holders.api.controller;

import com.robo.holders.domain.InsuranceHolderFinder;
import com.robo.holders.domain.NotFoundException;
import com.robo.holders.domain.Ssn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InsuranceHolderController {
    @Autowired
    private InsuranceHolderFinder insuranceHolderFinder;

    @GetMapping("/holders/{ssn}")
    public InsuranceHolderJson oneVehicle(@PathVariable String ssn) {
        return insuranceHolderFinder.find(Ssn.parse(ssn))
                .map(InsuranceHolderJson::from)
                .orElseThrow(() -> new NotFoundException("No holder found with id " + ssn));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException() {
        // ...
    }
}
