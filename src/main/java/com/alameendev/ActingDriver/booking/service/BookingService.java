package com.alameendev.ActingDriver.booking.service;

import com.alameendev.ActingDriver.booking.dto.BookingDTO;
import com.alameendev.ActingDriver.booking.entity.BookingStatus;

import java.util.List;

public interface BookingService {
    List<BookingDTO> retrieveAll();

    BookingDTO retrieveById(Long id);

    BookingDTO updateBookingStatuasById(Long id, BookingStatus status);

    BookingDTO createBookingByActorId(Long id, BookingDTO body);

    void deleteBookingById(Long id);

    List<BookingDTO> retrieveBookByActorId(Long id);
}
