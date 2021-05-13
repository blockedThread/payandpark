package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.BookingStatus;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.model.FetchBookingsRequest;
import com.payandpark.payandpark.booking.repository.BookingRepository;
import com.payandpark.payandpark.exception.BadRequestException;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;

import static com.payandpark.payandpark.parkingslot.model.ParkingSlotStatus.*;

import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
        ParkingSlot parkingSlot = parkingSlotService.fetchParkingSlotById(request.getParkingSlotId());

        if (UNAVAILABLE.name().equalsIgnoreCase(parkingSlot.getStatus())
                || BOOKED.name().equalsIgnoreCase(parkingSlot.getStatus())) {
            log.info("Parking slot :: {} is :: {}", request.getParkingSlotId(), parkingSlot.getStatus());
            throw new BadRequestException("Parking slot :: " + request.getParkingSlotId() + " is :: " + parkingSlot.getStatus());
        }

        BookingDetails bookingDetails = repository.createBooking(request);
        parkingSlotService.updateParkingSlotStatus(request.getParkingSlotId(), BOOKED.name());
        return bookingDetails;
    }

    @Override
    public BookingDetails endBooking(int bookingId) {
        log.info("Ending booking :: {}", bookingId);
        BookingDetails bookingDetails = repository.endBooking(bookingId);
        parkingSlotService.updateParkingSlotStatus(bookingDetails.getParkingSlotId(), AVAILABLE.name());
        return bookingDetails;
    }

    @Override
    public BookingDetails fetchBookingDetailsById(int bookingId) {
        log.info("Fetching booking details for id :: {}", bookingId);
        return repository.fetchBookingDetailsById(bookingId);
    }

    @Override
    public List<BookingDetails> fetchAllBookings(FetchBookingsRequest request) {

        if(!StringUtils.hasText(request.getStatus())) {
            log.error("Parameter status is null or empty for request :: {}", request.toString());
            throw new BadRequestException("Parameter status is null or empty for request :: " + request.toString());
        }

        String status = request.getStatus().toUpperCase();

        switch (BookingStatus.valueOf(status)) {
            case ALL:
                return repository.fetchAllBookings();

            case ACTIVE:
            case ENDED:
                log.info("Fetching {} bookings", status);
                return repository.fetchAllBookings(status);

            default:
                log.info("Undefined booking status :: {}", status);
                throw new BadRequestException("Undefined booking status :: " + status);
        }

    }
}
