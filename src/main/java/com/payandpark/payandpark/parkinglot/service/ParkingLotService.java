package com.payandpark.payandpark.parkinglot.service;

import com.payandpark.payandpark.parkinglot.model.AddParkingSlotInParkingLotRequest;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;

public interface ParkingLotService {
    ParkingLot fetchParkingLotById(int id);
    void addParkingLot();
    void addParkingSlotToParkingLot(AddParkingSlotInParkingLotRequest request);
}
