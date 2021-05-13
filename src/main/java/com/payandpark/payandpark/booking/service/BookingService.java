package com.payandpark.payandpark.booking.service;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.model.FetchBookingsRequest;

import java.util.List;

public interface BookingService {
    BookingDetails createBooking(CreateBookingRequest request);
    BookingDetails endBooking(int bookingId);
    BookingDetails fetchBookingDetailsById(int bookingId);
    List<BookingDetails> fetchAllBookings(FetchBookingsRequest request);
}
