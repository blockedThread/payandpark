package com.payandpark.payandpark.charge.model;

import lombok.Data;

@Data
public class Charge {
    private int id;
    private int vehicleTypeId;
    private float pricePerMinute;
}
