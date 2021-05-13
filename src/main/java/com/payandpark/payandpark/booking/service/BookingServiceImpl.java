package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.BookingStatus;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.repository.BookingRepository;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotStatus;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository repository;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Override
    public BookingDetails createBooking(CreateBookingRequest request) {
        log.info("Creating booking for request :: {}", request.toString());
        BookingDetails bookingDetails = repository.createBooking(request);
        parkingSlotService.updateParkingSlotStatus(request.getParkingSlotId(), ParkingSlotStatus.BOOKED.name());
        return bookingDetails;
    }

    @Override
    public BookingDetails endBooking(int bookingId) {
        log.info("Ending booking :: {}", bookingId);
        BookingDetails bookingDetails = repository.endBooking(bookingId);
        parkingSlotService.updateParkingSlotStatus(bookingDetails.getParkingSlotId(), ParkingSlotStatus.AVAILABLE.name());
        return bookingDetails;
    }
}
