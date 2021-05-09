package com.payandpark.payandpark.parkingslot.service;

import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.repository.ParkingSlotRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSlot fetchParkingSlotById(int id) {
        log.info("Fetching parking slot details by id :: {}", id);
        return parkingSlotRepository.fetchParkingSlotById(id);
    }

    @Override
    public List<ParkingSlot> fetchAllParkingSlotsByLotId(int parkingLotId) {
        log.info("Fetching all parking slot details in lot id :: {}", parkingLotId);
        return parkingSlotRepository.fetchAllParkingSlotsByLotId(parkingLotId);
    }

    @Override
    public ParkingSlot addParkingSlot(ParkingSlotRequest request) {
        log.info("Adding parking slot :: {}", request.toString());
        ParkingSlot parkingSlot = parkingSlotRepository.addParkingSlot(request);
        return parkingSlotRepository.fetchParkingSlotById(parkingSlot.getId());
    }

    @Override
    public void removeParkingSlot(int parkingSlotId) {
        parkingSlotRepository.removeParkingSlot(parkingSlotId);
    }
}
