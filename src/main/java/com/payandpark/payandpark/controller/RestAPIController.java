package com.payandpark.payandpark.controller;

import com.payandpark.payandpark.booking.model.BookingDetails;
import com.payandpark.payandpark.booking.model.CreateBookingRequest;
import com.payandpark.payandpark.booking.service.BookingService;
import com.payandpark.payandpark.parkinglot.model.AddParkingSlotInParkingLotRequest;
import com.payandpark.payandpark.parkinglot.model.ParkingLot;
import com.payandpark.payandpark.parkinglot.service.ParkingLotService;
import com.payandpark.payandpark.parkingslot.model.ParkingSlotRequest;
import com.payandpark.payandpark.parkingslot.model.ParkingSlot;
import com.payandpark.payandpark.parkingslot.service.ParkingSlotService;
import com.payandpark.payandpark.user.model.CreateUserRequest;
import com.payandpark.payandpark.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class RestAPIController {

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @GetMapping(value = "/parking/lot/fetch/{id}")
    public @ResponseBody
    ResponseEntity<ParkingLot> fetchParkingLotById(@PathVariable int id) {
        log.info("Request received to fetch parking lot for id :: {}", id);
        ParkingLot parkingLot = parkingLotService.fetchParkingLotById(id);
        return new ResponseEntity<>(parkingLot, HttpStatus.OK);
    }

    @GetMapping(value = "/parking/slot/fetch/{id}")
    public @ResponseBody
    ResponseEntity<ParkingSlot> fetchParkingSlotById(@PathVariable int id) {
        log.info("Request received to fetch parking slot by id :: {}", id);
        ParkingSlot parkingSlot = parkingSlotService.fetchParkingSlotById(id);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @PutMapping(value = "/parking/slot/add/")
    public @ResponseBody
    ResponseEntity<ParkingSlot> addParkingSlot(@RequestBody ParkingSlotRequest request)  {
        log.info("Request received to add parking slot :: {}", request.toString());
        ParkingSlot parkingSlot = parkingSlotService.addParkingSlot(request);
        return new ResponseEntity<>(parkingSlot, HttpStatus.OK);
    }

    @DeleteMapping(value = "/parking/slot/remove/{id}")
    ResponseEntity removeParkingSlot(@PathVariable int id) {
        log.info("Request received to remove parking slot of id :: {}", id);
        parkingSlotService.removeParkingSlot(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/parking/lot/add/slot")
    ResponseEntity addParkingSlotInParkingLot(@RequestBody AddParkingSlotInParkingLotRequest request) {
        log.info("Request received to add parking slot to parking lot :: {}", request.toString());
        parkingLotService.addParkingSlotToParkingLot(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/parking/user/create/")
    ResponseEntity createUser(@RequestBody CreateUserRequest request) {
        log.info("Request received to create new user for mobile :: {}", request.getMobile());
        userService.createUser(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/parking/booking/create/")
    ResponseEntity<BookingDetails> createBooking(@RequestBody CreateBookingRequest request) {
        log.info("request received to create booking :: {}", request.toString());
        BookingDetails bookingDetails = bookingService.createBooking(request);
        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/parking/booking/end/{bookingId}")
    ResponseEntity<BookingDetails> endBooking(@PathVariable int bookingId) {
        log.info("Request received to end booking :: {}", bookingId);
        BookingDetails bookingDetails = bookingService.endBooking(bookingId);
        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
    }
}
