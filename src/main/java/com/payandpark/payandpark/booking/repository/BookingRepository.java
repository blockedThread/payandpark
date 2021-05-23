package com.payandpark.payandpark.booking.repository;

import com.payandpark.payandpark.booking.model.Booking;
import com.payandpark.payandpark.charge.model.Charge;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.exception.ResourceNotFoundException;
import com.payandpark.payandpark.exception.ResourceNotSavedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Booking createBooking(CreateBookingRequest request) {
        String sql = "insert into pl.booking (userid, parking_slot_id, starttime)\n" +
                "values ("+ request.getUserId()+ "," + request.getParkingSlotId()+ ", now())\n" +
                "returning id, userid as userId, parking_slot_id as parkingSlotId, starttime as startTime, status";

        try {
            log.info("Query :: {}", sql);
            Booking booking = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Booking.class));
            log.info("Booking created :: {}", booking.toString());
            return booking;
        } catch (Exception e) {
            log.error("Error occurred while creating booking for request :: {}", request.toString());
            throw new ResourceNotSavedException("Error occurred while creating booking for request :: " + request.toString());
        }
    }

    public Booking endBooking(int bookingId) {
        String sql = "update pl.booking set endtime = now(), status = 'ENDED' where id = " + bookingId + "\n" +
                "and status = 'ACTIVE' returning id, userid as userId, parking_slot_id as parkingSlotId, starttime as startTime, endtime as endTime, status";
        try {
            log.info("Query :: {}", sql);
            Booking booking = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Booking.class));
            log.info("Booking ended :: {}", booking);
            return booking;
        } catch (Exception e) {
            log.error("Error occurred while ending booking :: {}", bookingId);
            throw new ResourceNotSavedException("Error occurred while ending booking :: " + bookingId);
        }
    }

    public List<Booking> fetchAllBookingsByStatus(String status) {
        String sql = "select id, userId, parking_slot_id as parkingSlotId, startTime, endTime, status from pl.booking\n" +
                "where status = '" + status + "'";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("No bookings with status :: {}", status);
            return new ArrayList<>();
        }
    }

    public List<Booking> fetchAllBookingsByUserId(int userId) {
        String sql = "select id, userId, parking_slot_id as parkingSlotId, startTime, endTime, status from pl.booking\n" +
                "where userId = '" + userId + "'";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("No bookings for userId :: {}", userId);
            return new ArrayList<>();
        }
    }

    public Booking fetchBookingDetailsById(int bookingId) {
        String sql = "select id, userId, parking_slot_id as parkingSlotId, startTime, endTime, status, price from pl.booking\n" +
                "where id = '" + bookingId + "'";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("Booking not found for id :: {}", bookingId);
            throw new ResourceNotFoundException("Booking not found for id :: " + bookingId);
        }
    }

    public List<Booking> fetchAllBookings() {
        String sql = "select id, userId, parking_slot_id as parkingSlotId, startTime, endTime, status from pl.booking order by startTime desc";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (EmptyResultDataAccessException e) {
            log.info("No bookings found");
            return new ArrayList<>();
        }
    }

    public Booking updateBookingPrice(int bookingId, int price) {
        String sql = "update pl.booking set price = " + price + " where id = " + bookingId
                + " returning id, userId, parking_slot_id as parkingSlotId, startTime, endTime, status, price";
        try {
            log.info("Query :: {}", sql);
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Booking.class));
        } catch (Exception e) {
            log.error("Error occurred while updating booking price :: {}", bookingId);
            throw new ResourceNotFoundException("Error occurred while updating booking price :: " + bookingId);
        }
    }

}
