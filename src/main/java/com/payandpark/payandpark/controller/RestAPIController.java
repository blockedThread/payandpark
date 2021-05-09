package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkinglot.service.ParkingLotService;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class RestAPIController {

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    ParkingSlotService parkingSlotService;

    @GetMapping(value = "/parking/lot/{id}")
    public @ResponseBody
    ResponseEntity<ParkingLot> fetchParkingLotById(@PathVariable int id) {
        log.info("Request received to fetch parking lot for id :: {}", id);
        ParkingLot parkingLot = parkingLotService.fetchParkingLotById(id);
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    @GetMapping(value = "/parking/slot/{id}")
    public @ResponseBody
    ResponseEntity<ParkingSlot> fetchParkingSlotById(@PathVariable int id) {
        log.info("Request received to fetch parking slot by id :: {}", id);
        ParkingSlot parkingSlot = parkingSlotService.fetchParkingSlotById(id);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @PutMapping(value = "/parking/slot/")
    public @ResponseBody
    ResponseEntity<ParkingSlot> addParkingSlot(@RequestBody ParkingSlotRequest request)  {
        log.info("Request received to add parking slot :: {}", request.toString());
        ParkingSlot parkingSlot = parkingSlotService.addParkingSlot(request);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @DeleteMapping(value = "/parking/slot/{id}")
    ResponseEntity removeParkingSlot(@PathVariable int id) {
        log.info("Request received to remove parking slot of id :: {}", id);
        parkingSlotService.removeParkingSlot(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
