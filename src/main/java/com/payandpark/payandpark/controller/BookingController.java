package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.booking.model.Booking;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.model.FetchBookingsRequest;
import com.payandpark.payandpark.booking.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Log4j2
@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PutMapping(value = "/parking/booking/create/")
    ResponseEntity<Booking> createBooking(@RequestBody CreateBookingRequest request) {
        log.info("request received to create booking :: {}", request.toString());
        Booking booking = bookingService.createBooking(request);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping(value = "/parking/booking/end/{bookingId}")
    ResponseEntity<Booking> endBooking(@PathVariable int bookingId) {
        log.info("Request received to end booking :: {}", bookingId);
        Booking booking = bookingService.endBooking(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping(value = "/parking/booking/fetch/all/")
    ResponseEntity<List<Booking>> fetchAllBookingDetails(@RequestBody FetchBookingsRequest request) {
        log.info("Request received to fetch all booking details :: {}", request.toString());
        List<Booking> bookingList = bookingService.fetchAllBookings(request);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping(value = "/parking/booking/fetch/{bookingId}")
    ResponseEntity<Booking> fetchBookingDetailsById(@PathVariable int bookingId) {
        log.info("Request received to fetch booking details for id :: {}", bookingId);
        Booking booking = bookingService.fetchBookingDetailsById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
