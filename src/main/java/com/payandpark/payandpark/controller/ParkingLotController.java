package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.parkinglot.model.AddParkingSlotInParkingLotRequest;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkinglot.service.ParkingLotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @GetMapping(value = "/parking/lot/fetch/{id}")
    public @ResponseBody
    ResponseEntity<ParkingLot> fetchParkingLotById(@PathVariable int id) {
        log.info("Request received to fetch parking lot for id :: {}", id);
        ParkingLot parkingLot = parkingLotService.fetchParkingLotById(id);
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    @PostMapping(value = "/parking/lot/add/slot")
    ResponseEntity addParkingSlotInParkingLot(@RequestBody AddParkingSlotInParkingLotRequest request) {
        log.info("Request received to add parking slot to parking lot :: {}", request.toString());
        parkingLotService.addParkingSlotToParkingLot(request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
