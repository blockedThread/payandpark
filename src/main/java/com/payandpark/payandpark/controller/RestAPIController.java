package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkinglot.service.ParkingLotService;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    ParkingLot fetchParkingLotById(@PathVariable int id) {
        log.info("Request received to fetch parking lot for id :: {}", id);
        ParkingLot parkingLot = parkingLotService.fetchParkingLotById(id);
        return parkingLot;
    }

    @GetMapping(value = "/parking/slot/{id}")
    public @ResponseBody
    ParkingSlot fetchParkingSlotById(@PathVariable int id) {
        log.info("Request received to fetch parking slot by id :: {}", id);
        ParkingSlot parkingslot = parkingSlotService.fetchParkingSlotById(id);
        return parkingslot;
    }
}
