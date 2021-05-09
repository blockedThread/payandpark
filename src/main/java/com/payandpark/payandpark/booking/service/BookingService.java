package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;

public interface BookingService {
    BookingDetails createBooking(CreateBookingRequest request);
    BookingDetails endBooking(int bookingId);
}
