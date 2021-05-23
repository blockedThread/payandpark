package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.charge.model.Charge;
import com.payandpark.payandpark.charge.model.ChargeDetailsRequest;
import com.payandpark.payandpark.charge.service.ChargingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@Controller
public class ChargesController {

    @Autowired
    ChargingService chargingService;

    @PutMapping(value = "/parking/charges/create/")
    ResponseEntity<Charge> createCharge(@RequestBody ChargeDetailsRequest request) {
        log.info("request received to create charges :: {}", request.toString());
        Charge charge = chargingService.createChargeDetails(request);
        return new ResponseEntity<>(charge, HttpStatus.OK);
    }
}
