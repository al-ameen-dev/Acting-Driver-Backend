package com.alameendev.ActingDriver.job.entity;

import com.alameendev.ActingDriver.client.entity.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "location")
    private String location;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    @Column(name = "time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    @Column(name = "jobStatus")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
}
