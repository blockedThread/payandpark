package com.payandpark.payandpark.booking.model;

import lombok.Data;

@Data
public class FetchBookingsRequest {
    private Integer userId;
    private String status;
}
