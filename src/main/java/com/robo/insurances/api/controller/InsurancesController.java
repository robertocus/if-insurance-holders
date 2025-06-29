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

    @GetMapping("/insurances")
    public InsurancesJson insurancesByHolderId(@RequestParam("byHolderId") String holderId) {
        if (holderId == null) {
            throw new IllegalArgumentException("Missing query param byHolderId");
        }
        var insurances = insuranceFinder.findByHolder(HolderId.parse(holderId));
        if (insurances.isEmpty()) {
            throw new NotFoundException("No insurances found for holder " + holderId);
        }
        return InsurancesJson.from(insurances);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException() {
        // ...
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequest() {
        // ...
    }
}
