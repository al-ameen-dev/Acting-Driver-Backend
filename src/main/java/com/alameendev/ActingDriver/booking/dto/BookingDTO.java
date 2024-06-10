package com.alameendev.ActingDriver.booking.dto;

import com.alameendev.ActingDriver.booking.entity.BookingStatus;
import com.alameendev.ActingDriver.booking.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long bookingId;
    private Long jobId;
    private Long clientId;
    private Long actorId;
    private String bookingDate;
    private String bookingTime;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
}
