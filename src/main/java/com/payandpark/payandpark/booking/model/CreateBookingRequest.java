package com.payandpark.payandpark.booking.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateBookingRequest {
    @NonNull
    private int userId;
    @NonNull
    private int parkingSlotId;
}
