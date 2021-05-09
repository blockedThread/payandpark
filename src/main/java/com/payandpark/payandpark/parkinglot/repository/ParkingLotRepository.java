package com.payandpark.payandpark.parkinglot.repository;

import com.payandpark.payandpark.exception.ResourceNotFoundException;
import com.payandpark.payandpark.exception.ResourceNotSavedException;
import com.payandpark.payandpark.parkinglot.model.AddParkingSlotInParkingLotRequest;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class ParkingLotRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ParkingLot fetchParkingLotById(int id) {
        String sql = "select id from pl.parking_lot where id = " + id;
        try {
            log.info("Query :: {}", sql);
            ParkingLot parkingLot =  jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ParkingLot.class));
            log.info("Parking lot details :: {} for id :: {}", parkingLot.toString(), id);
            return parkingLot;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Parking lot not found with id: " + id);
        }
    }

    public void addParkingSlotToParkingLot(AddParkingSlotInParkingLotRequest request) {
        String sql = "insert into pl.parking_lot_slot_mapping (parking_lot_id, parking_slot_id)\n" +
                "values(" + request.getParkingLotId() + ", "+ request.getParkingSlotId()+")";
        try {
            log.info("Query :: {}", sql);
            jdbcTemplate.execute(sql);
        } catch (ResourceNotSavedException e) {
            log.error("Unable to add parking slot to parking lot for request :: {}", request.toString());
            throw new ResourceNotSavedException("Unable to add parking slot to parking lot");
        }
    }
}
