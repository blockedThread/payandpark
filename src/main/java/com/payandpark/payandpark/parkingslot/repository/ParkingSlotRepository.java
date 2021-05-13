package com.payandpark.payandpark.parkingslot.repository;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.exception.ResourceNotFoundException;
import com.payandpark.payandpark.exception.ResourceNotSavedException;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class ParkingSlotRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ParkingSlot fetchParkingSlotById(int id) {
        String sql = "select ps.id, vt.type, ps.status from\n" +
                "pl.parking_slot ps\n" +
                "inner join\n" +
                "pl.vehicle_type vt\n" +
                "on ps.vehicle_type_id = vt.id where ps.id = " + id;
        try {
            log.info("Query :: {}", sql);
            ParkingSlot parkingSlot = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
            log.info("Parking slot details :: {} for id :: {}", parkingSlot.toString(), id);
            return parkingSlot;
            } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Parking slot not found with id: " + id);
        }
    }

    public List<ParkingSlot> fetchAllParkingSlotsByLotId(int parkingLotId) {
        String sql = "select ps.id, vt.type, ps.status from \n" +
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
            List<ParkingSlot> parkingSlots = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
            log.info("Parking slot details :: {} for parking lot id :: {}", parkingSlots.toString(), parkingLotId);
            return parkingSlots;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ParkingSlot addParkingSlot(ParkingSlotRequest request) {
        String sql = "insert into pl.parking_slot (vehicle_type_id)\n" +
                "values (" + request.getType() + ") returning id";
        ParkingSlot parkingSlot = null;
        try {
            log.info("Query :: {}", sql);
            parkingSlot = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
        } catch (Exception e) {
            log.error("Error occurred while inserting parking slot :: {} cause :: {}", request.toString(), e.getCause());
            throw new ResourceNotSavedException("Error occurred while inserting parking slot");
        }

        return parkingSlot;
    }

    public void removeParkingSlot(int parkingSlotId) {
        String sql = "delete from pl.parking_slot where id = " + parkingSlotId;
        try {
            log.info("Query :: {}", sql);
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            log.error("Error occurred while deleting parking slot :: {} cause :: {}", parkingSlotId, e.getCause());
        }
    }

    public void updateParkingSlotStatus(int parkingSlotId, String status) {
        String sql = "update pl.parking_slot set status = '" + status + "' where id = " + parkingSlotId;
        try {
            log.info("Query :: {}", sql);
            jdbcTemplate.execute(sql);
            log.info("Updated parking slot :: {} status to :: {}", parkingSlotId, status);
        } catch (Exception e) {
            log.error("Error occurred while updating parking slot status for slot :: {}", parkingSlotId);
            throw new ResourceNotSavedException("Error occurred while updating parking slot status for slot :: " + parkingSlotId);
        }
    }

    public List<ParkingSlot> fetchParkingSlotsForStatus(String status) {
        String sql = "select ps.id, vt.type, ps.status from \n" +
                "pl.parking_slot ps\n" +
                "inner join\n" +
                "pl.vehicle_type vt\n" +
                "on ps.vehicle_type_id = vt.id \n" +
                "where ps.status = '"+ status +"'";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
        } catch (Exception e) {
            log.error("Error occurred while fetching available slots");
            throw new ResourceNotFoundException("Error occurred while fetching available slots");
        }
    }

    public List<ParkingSlot> fetchAllParkingSlots() {
        String sql = "select ps.id, vt.type, ps.status from \n" +
                "pl.parking_slot ps\n" +
                "inner join\n" +
                "pl.vehicle_type vt\n" +
                "on ps.vehicle_type_id = vt.id";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ParkingSlot.class));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error occurred while fetching all parking slots :: {}", e.getCause());
            throw new ResourceNotFoundException("Error occurred while fetching all parking slots");
        }
    }
}
