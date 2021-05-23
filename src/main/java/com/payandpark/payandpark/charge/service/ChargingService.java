package com.payandpark.payandpark.charge.service;

import com.payandpark.payandpark.charge.model.Charge;
import com.payandpark.payandpark.charge.model.ChargeDetailsRequest;

public interface ChargingService {
    Charge createChargeDetails(ChargeDetailsRequest request);
    Charge fetchChargeByVehicleTypeId(int vehicleTypeId);
}
