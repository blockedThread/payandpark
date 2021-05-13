package com.payandpark.payandpark.parkingslot.service;

import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    ParkingSlot fetchParkingSlotById(int id);
    List<ParkingSlot> fetchAllParkingSlotsByLotId(int parkingLotId);
    ParkingSlot addParkingSlot(ParkingSlotRequest request);
    void updateParkingSlotStatus(int parkingSlotId, String status);
    void removeParkingSlot(int id);
    List<ParkingSlot> fetchAllParkingSlots(ParkingSlotRequest request);
}
