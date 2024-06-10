package com.alameendev.ActingDriver.job.dto;

import com.alameendev.ActingDriver.job.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO{
    private Long jobId;
    private Long clientId;
    private String title;
    private String description;
    private String requirements;
    private String pickupLocation;
    private String dropOffLocation;
    private LocalDate date;
    private LocalTime time;
    private JobStatus jobStatus;
}
