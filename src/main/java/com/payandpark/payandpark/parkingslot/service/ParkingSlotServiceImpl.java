package com.payandpark.payandpark.parkingslot.service;

import com.payandpark.payandpark.exception.BadRequestException;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotStatus;
import com.payandpark.payandpark.parkingslot.repository.ParkingSlotRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public void updateParkingSlotStatus(int parkingSlotId, String status) {
        log.info("Updating parking slot status for slot id :: {}, status :: {}", parkingSlotId, status);
        parkingSlotRepository.updateParkingSlotStatus(parkingSlotId, status);
    }

    @Override
    public void removeParkingSlot(int parkingSlotId) {
        log.info("Removing parking slot :: {}", parkingSlotId);
        parkingSlotRepository.removeParkingSlot(parkingSlotId);
    }

    @Override
    public List<ParkingSlot> fetchAllParkingSlots(ParkingSlotRequest request) {

        if (!StringUtils.hasText(request.getStatus())) {
            log.error("Parameter status is null or empty in request :: {}", request.toString());
            throw new BadRequestException("Parameter <status> is null or empty");
        }

        String status = request.getStatus().toUpperCase();

        switch (ParkingSlotStatus.valueOf(status)) {
            case ALL:
                log.info("Fetching ALL parking slots");
                return parkingSlotRepository.fetchAllParkingSlots();

            case BOOKED:
            case AVAILABLE:
            case UNAVAILABLE:
                log.info("Fetching {} parking slots", status);
                return parkingSlotRepository.fetchParkingSlotsForStatus(status);

            default:
                log.error("Undefined slot status request :: {}", status);
                throw new BadRequestException("Undefined slot status request :: " + status);
        }
    }
}
