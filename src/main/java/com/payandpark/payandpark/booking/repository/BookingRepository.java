package com.payandpark.payandpark.booking.repository;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.exception.ResourceNotSavedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public BookingDetails createBooking(CreateBookingRequest request) {
        String sql = "insert into pl.booking (userid, parking_slot_id, starttime)\n" +
                "values ("+ request.getUserId()+ "," + request.getParkingSlotId()+ ", now())\n" +
                "returning userid as userId, parking_slot_id as parkingSlotId, starttime as startTime, status";
        try {
            log.info("Query :: {}", sql);
            BookingDetails bookingDetails = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookingDetails.class));
            log.info("Booking created :: {}", bookingDetails.toString());
            return bookingDetails;
        } catch (Exception e) {
            log.error("Error occurred while creating booking for request :: {}", request.toString());
            throw new ResourceNotSavedException("Error occurred while creating booking for request :: " + request.toString());
        }
    }

    public BookingDetails endBooking(int bookingId) {
        String sql = "update pl.booking set endtime = now(), status = 'ENDED' where id = " + bookingId + "\n" +
                "returning userid as userId, parking_slot_id as parkingSlotId, starttime as startTime, endtime as endTime, status";
        try {
            log.info("Query :: {}", sql);
            BookingDetails bookingDetails = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookingDetails.class));
            log.info("Booking ended :: {}", bookingDetails);
            return bookingDetails;
        } catch (Exception e) {
            log.error("Error occurred while ending booking :: {}", bookingId);
            throw new ResourceNotSavedException("Error occurred while ending booking :: " + bookingId);
        }
    }


}
