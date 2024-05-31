package com.alameendev.ActingDriver.booking.entity;


import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.job.entity.Job;
import com.alameendev.ActingDriver.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "jobId",nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "actorId",nullable = false)
    private Actor actor;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @Temporal(TemporalType.TIME)
    private OffsetTime bookingTime;

    @OneToMany(mappedBy = "booking",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Column(name = "paymentStatus")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;




}
