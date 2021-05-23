package com.payandpark.payandpark.parkingslot.model;

import lombok.Data;

@Data
public class ParkingSlot {
    private int id;
    private int vehicleTypeId;
    private String type;
    private String status;
}
