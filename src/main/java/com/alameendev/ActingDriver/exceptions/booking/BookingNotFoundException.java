package com.alameendev.ActingDriver.exceptions.booking;

public class BookingNotFoundException extends IllegalArgumentException{

    public BookingNotFoundException(Long id){
        super("The Booking with "+id+" not found!");
    }
}
