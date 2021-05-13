package com.payandpark.payandpark.parkingslot.model;

import lombok.Data;

@Data
public class ParkingSlotRequest {
    private Integer id;
    private Integer type;
    private String status;
}
