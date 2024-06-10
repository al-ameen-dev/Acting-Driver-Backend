package com.alameendev.ActingDriver.booking.repository;

import com.alameendev.ActingDriver.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findAllByActorActorId(Long id);
}
