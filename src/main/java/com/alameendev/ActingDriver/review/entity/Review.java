package com.alameendev.ActingDriver.review.entity;


import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.booking.entity.Booking;
import com.alameendev.ActingDriver.client.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "bookingId",nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "clientId",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "actorId",nullable = false)
    private Actor actor;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comments")
    private String comments;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "postedDateAndTime")
    private Date date;

}