package com.payandpark.payandpark.parkinglot.model;

import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import lombok.Data;

import java.util.List;

@Data
public class ParkingLot {
    private int id;
    private String status;
    private List<ParkingSlot> parkingSlotSet;
}
