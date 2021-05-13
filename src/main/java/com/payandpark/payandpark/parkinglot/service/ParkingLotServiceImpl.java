package com.payandpark.payandpark.parkinglot.service;

import com.payandpark.payandpark.parkinglot.model.AddParkingSlotInParkingLotRequest;
import com.payandpark.payandpark.parkinglot.repository.ParkingLotRepository;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotStatus;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ParkingLotServiceImpl implements ParkingLotService{

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Override
    public ParkingLot fetchParkingLotById(int id) {
        log.info("Fetching parking lot by id :: {}",id);
        ParkingLot parkingLot =  parkingLotRepository.fetchParkingLotById(id);
        List<ParkingSlot> parkingSlots = parkingSlotService.fetchAllParkingSlotsByLotId(id);
        parkingLot.setParkingSlotSet(parkingSlots);
        return parkingLot;
    }

    @Override
    public void addParkingLot() {
        //TODO:: Implementation
    }

    @Override
    public void addParkingSlotToParkingLot(AddParkingSlotInParkingLotRequest request) {
        log.info("Adding parking slot :: {} to parking lot :: {}", request.getParkingSlotId(), request.getParkingLotId());
        parkingLotRepository.addParkingSlotToParkingLot(request);
        log.info("Updating parking slot :: {} status to available", request.getParkingSlotId());
        parkingSlotService.updateParkingSlotStatus(request.getParkingSlotId(), ParkingSlotStatus.AVAILABLE.name());
    }
}
