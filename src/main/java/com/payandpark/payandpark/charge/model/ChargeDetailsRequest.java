package com.payandpark.payandpark.charge.model;

import lombok.Data;

@Data
public class ChargeDetailsRequest {
    private int vehicleTypeId;
    private float pricePerMinute;
}
