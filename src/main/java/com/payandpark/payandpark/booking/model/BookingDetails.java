package com.payandpark.payandpark.booking.model;

import lombok.Data;

@Data
public class BookingDetails {
    private int userId;
    private int parkingSlotId;
    private String startTime;
    private String endTime;
    private String status;
}
