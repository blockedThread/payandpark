package com.payandpark.payandpark.parkingslot.service;

import com.payandpark.payandpark.parkingslot.model.AddParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    ParkingSlot fetchParkingSlotById(int id);
    List<ParkingSlot> fetchAllParkingSlotsByLotId(int parkingLotId);
    ParkingSlot insertParkingSlot(AddParkingSlotRequest request);
}
