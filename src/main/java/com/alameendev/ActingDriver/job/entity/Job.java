package com.alameendev.ActingDriver.job.entity;

import com.alameendev.ActingDriver.client.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "clientId",nullable = false)
    private Client client;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "pickupLocation")
    private String pickupLocation;

    @Column(name= "dropOffLocation")
    private String dropOffLocation;

    @Temporal(TemporalType.DATE)
    @Column(name = "jobPostedDate")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "jobPostedTime")
    private LocalTime time;

    @Column(name = "jobStatus")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
}
