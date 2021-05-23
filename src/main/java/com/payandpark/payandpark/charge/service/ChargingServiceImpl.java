package com.payandpark.payandpark.charge.service;

import com.payandpark.payandpark.charge.model.Charge;
import com.payandpark.payandpark.charge.model.ChargeDetailsRequest;
import com.payandpark.payandpark.charge.repository.ChargeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ChargingServiceImpl implements ChargingService{

    @Autowired
    ChargeRepository chargeRepository;

    @Override
    public Charge createChargeDetails(ChargeDetailsRequest request) {
        log.info("Create charge details :: {}", request.toString());
        return chargeRepository.createCharge(request);
    }

    @Override
    public Charge fetchChargeByVehicleTypeId(int vehicleTypeId) {
        log.info("Fetching charge details for vehicle type id :: {}", vehicleTypeId);
        return chargeRepository.fetchChargeByVehicleType(vehicleTypeId);
    }
}
