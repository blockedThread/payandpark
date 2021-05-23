package com.payandpark.payandpark.charge.repository;

import com.payandpark.payandpark.charge.model.Charge;
import com.payandpark.payandpark.charge.model.ChargeDetailsRequest;
import com.payandpark.payandpark.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class ChargeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Charge createCharge(ChargeDetailsRequest request) {
        String sql = "insert into pl.charges (vehicle_type_id, price_per_minute) values (" + request.getVehicleTypeId() + "," + request.getPricePerMinute() + ")" +
                " returning vehicle_type_id as vehicleTypeId, price_per_minute as pricePerMinute";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Charge.class));
        } catch (Exception e) {
            log.error("Error occurred while inserting charging details for vehicle type id :: {} and price :: {}", request.getVehicleTypeId(), request.getPricePerMinute());
            throw new ResourceNotFoundException("Error occurred while inserting charging details for vehicle type id :: " + request.getVehicleTypeId() + " and price :: " + request.getPricePerMinute());
        }
    }

    public Charge fetchChargeByVehicleType(int vehicleTypeId) {
        String sql = "select vehicle_type_id, price_per_minute from pl.charges where vehicle_type_id = " + vehicleTypeId;
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Charge.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("Charge details not found for vehicle type id :: {}", vehicleTypeId);
            throw new ResourceNotFoundException("Charge details not found for vehicle type id :: " + vehicleTypeId);
        }
    }
}
