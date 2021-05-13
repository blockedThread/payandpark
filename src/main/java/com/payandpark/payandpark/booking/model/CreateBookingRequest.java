package com.payandpark.payandpark.booking.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateBookingRequest {
    @NonNull
    private Integer userId;
    @NonNull
    private Integer parkingSlotId;
}
