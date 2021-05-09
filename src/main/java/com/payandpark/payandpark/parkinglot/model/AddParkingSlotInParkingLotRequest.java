package com.payandpark.payandpark.parkinglot.model;

import lombok.Data;

@Data
public class AddParkingSlotInParkingLotRequest {
    int parkingLotId;
    int parkingSlotId;
}
