package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
public class ParkingSlotController {

    @Autowired
    ParkingSlotService parkingSlotService;

    @GetMapping(value = "/parking/slot/fetch/{id}")
    public @ResponseBody
    ResponseEntity<ParkingSlot> fetchParkingSlotById(@PathVariable int id) {
        log.info("Request received to fetch parking slot by id :: {}", id);
        ParkingSlot parkingSlot = parkingSlotService.fetchParkingSlotById(id);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @PutMapping(value = "/parking/slot/add/")
    public @ResponseBody
    ResponseEntity<ParkingSlot> addParkingSlot(@RequestBody ParkingSlotRequest request)  {
        log.info("Request received to add parking slot :: {}", request.toString());
        ParkingSlot parkingSlot = parkingSlotService.addParkingSlot(request);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @DeleteMapping(value = "/parking/slot/remove/{id}")
    ResponseEntity removeParkingSlot(@PathVariable int id) {
        log.info("Request received to remove parking slot of id :: {}", id);
        parkingSlotService.removeParkingSlot(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/parking/slot/fetch/all/")
    ResponseEntity<List<ParkingSlot>> fetchAllParkingSlot(@RequestBody ParkingSlotRequest request) {
        log.info("Request received to fetch all parking slots :: {}", request.toString());
        List<ParkingSlot> parkingSlots = parkingSlotService.fetchAllParkingSlots(request);
        return new ResponseEntity<>(parkingSlots, HttpStatus.OK);
    }
}
