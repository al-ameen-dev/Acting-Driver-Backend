package com.alameendev.ActingDriver.booking.service;

import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.service.ActorService;
import com.alameendev.ActingDriver.booking.dto.BookingDTO;
import com.alameendev.ActingDriver.booking.entity.Booking;
import com.alameendev.ActingDriver.booking.entity.BookingStatus;
import com.alameendev.ActingDriver.booking.repository.BookingRepository;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.booking.BookingNotFoundException;
import com.alameendev.ActingDriver.job.entity.Job;
import com.alameendev.ActingDriver.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final ActorService actorService;
    private final ClientService clientService;
    private final JobService jobService;


    @Override
    public List<BookingDTO> retrieveAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(booking->modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookingDTO retrieveById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new BookingNotFoundException(id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public BookingDTO updateBookingStatuasById(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        booking.setBookingStatus(status);
        bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public BookingDTO createBookingByActorId(Long id, BookingDTO body) {
        Actor actor = actorService.retrieveActorById(id);
        Client client = clientService.retrieveClientById(body.getClientId());
        Job job = jobService.retrieveJobEntityById(body.getJobId());
        Booking booking = Booking.builder()
                .client(client)
                .actor(actor)
                .job(job)
                .bookingDate(LocalDate.parse(body.getBookingDate()))
                .bookingTime(LocalTime.parse(body.getBookingTime()))
                .bookingStatus(body.getBookingStatus())
                .paymentStatus(body.getPaymentStatus())
                .build();
        bookingRepository.save(booking);
        return modelMapper.map(booking,BookingDTO.class);
    }

    @Override
    public void deleteBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingDTO> retrieveBookByActorId(Long id) {
        List<Booking> bookings = bookingRepository.findAllByActorActorId(id);
        return bookings.stream().map(booking -> modelMapper.map(booking,BookingDTO.class)).collect(Collectors.toList());
    }

}
