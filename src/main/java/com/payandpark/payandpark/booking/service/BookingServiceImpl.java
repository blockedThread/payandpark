package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.repository.BookingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository repository;

    @Override
    public BookingDetails createBooking(CreateBookingRequest request) {
        log.info("Creating booking for request :: {}", request.toString());
        return repository.createBooking(request);

        //TODO:: Update slot status
    }

    @Override
    public BookingDetails endBooking(int bookingId) {
        log.info("Ending booking :: {}", bookingId);
        return repository.endBooking(bookingId);

        //TODO:: Update slot status
    }
}
