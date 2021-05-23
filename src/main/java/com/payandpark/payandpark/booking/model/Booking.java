package com.payandpark.payandpark.booking.model;

import lombok.Data;

@Data
public class Booking {
    private int id;
    private int userId;
    private int parkingSlotId;
    private String startTime;
    private String endTime;
    private int price;
    private String status;
}
