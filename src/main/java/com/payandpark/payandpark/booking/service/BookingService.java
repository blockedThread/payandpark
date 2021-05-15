package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.Booking;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.model.FetchBookingsRequest;

import java.util.List;

public interface BookingService {
    Booking createBooking(CreateBookingRequest request);
    Booking endBooking(int bookingId);
    Booking fetchBookingDetailsById(int bookingId);
    List<Booking> fetchAllBookings(FetchBookingsRequest request);
}
