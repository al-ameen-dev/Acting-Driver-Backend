package com.alameendev.ActingDriver.booking.controller;

import com.alameendev.ActingDriver.booking.dto.BookingDTO;
import com.alameendev.ActingDriver.booking.entity.BookingStatus;
import com.alameendev.ActingDriver.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "API endpoint for retrieving all the bookings using GET Request!")
    @GetMapping("")
    public List<BookingDTO> getAll(){
        return bookingService.retrieveAll();
    }

    @Operation(summary = "API endpoint for retrieving the specific Booking by booking Id using GET Request!")
    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id){
        return bookingService.retrieveById(id);
    }

    @Operation(summary = "API endpoint for Creating the Booking by Actor id using POST Request!")
    @PostMapping("/actor/{id}")
    public BookingDTO createBookingByActorId(@PathVariable Long id, @RequestBody BookingDTO body){
        return bookingService.createBookingByActorId(id,body);
    }

    @Operation(summary = "API endpoint for Updating the status of the Booking by Booking by Id Using PUT Request")
    @PutMapping("/{id}")
    public BookingDTO updateStatus(@PathVariable Long id, @RequestBody BookingStatus status){
        return bookingService.updateBookingStatuasById(id,status);
    }

    @Operation(summary = "API endpoint for Deleting the booking by Booking Id using DELETE Request!")
    @DeleteMapping("/{id}")
    public void deleteBookingById(@PathVariable Long id){
        bookingService.deleteBookingById(id);
    }

    @Operation(summary = "API endpoint for Retrieiving bookings for the specific Actor by Id using GET Request!")
    @GetMapping("/actor/{id}")
    public List<BookingDTO> getBookingsForTheActor(@PathVariable Long id){
        return bookingService.retrieveBookByActorId(id);
    }
}
