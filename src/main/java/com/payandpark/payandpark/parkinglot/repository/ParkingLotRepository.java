package com.payandpark.payandpark.parkinglot.repository;

import com.payandpark.payandpark.Exception.ResourceNotFoundException;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkinglot.model.ParkingLotMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class ParkingLotRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ParkingLot fetchParkingLotById(int id) {
        String sql = "SELECT id from pl.parking_lot where id = " + id;
        try {
            log.info("Query :: {}", sql);
            ParkingLot parkingLot =  jdbcTemplate.queryForObject(sql, new ParkingLotMapper());
            log.info("Parking lot details :: {} for id :: {}", parkingLot.toString(), id);
            return parkingLot;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Parking lot not found with id: " + id);
        }
    }
}
