package com.payandpark.payandpark.parkingslot.repository;

import com.payandpark.payandpark.Exception.ResourceNotFoundException;
import com.payandpark.payandpark.parkingslot.model.AddParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRowMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class ParkingSlotRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ParkingSlot fetchParkingSlotById(int id) {
        String sql = "select ps.id, vt.type from\n" +
                "pl.parking_slot ps\n" +
                "inner join\n" +
                "pl.vehicle_type vt\n" +
                "on ps.vehicle_type_id = vt.id where ps.id = " + id;
        try {
            log.info("Query :: {}", sql);
            ParkingSlot parkingSlot = jdbcTemplate.queryForObject(sql, new ParkingSlotRowMapper());
            log.info("Parking slot details :: {} for id :: {}", parkingSlot.toString(), id);
            return parkingSlot;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Parking slot not found with id: " + id);
        }
    }

    public List<ParkingSlot> fetchAllParkingSlotsByLotId(int parkingLotId) {
        String sql = "select ps.id, vt.type from \n" +
                "pl.parking_lot pl\n" +
                "inner join\n" +
                "pl.parking_lot_slot_mapping plsm\n" +
                "on pl.id = plsm.parking_lot_id and pl.id = " + parkingLotId +
                "\ninner join\n" +
                "pl.parking_slot ps\n" +
                "on ps.id = plsm.parking_slot_id\n" +
                "inner join\n" +
                "pl.vehicle_type vt\n" +
                "on vt.id = ps.vehicle_type_id";
        try {
            log.info("Query :: {}", sql);
            List<ParkingSlot> parkingSlots = jdbcTemplate.query(sql, new ParkingSlotRowMapper());
            log.info("Parking slot details :: {} for parking lot id :: {}", parkingSlots.toString(), parkingLotId);
            return parkingSlots;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ParkingSlot addParkingSlot(AddParkingSlotRequest request) {
        String sql = "insert into pl.parkingslot (type)\n" +
                "values ("+ request.getType() + ")";

        return null;
    }
}
