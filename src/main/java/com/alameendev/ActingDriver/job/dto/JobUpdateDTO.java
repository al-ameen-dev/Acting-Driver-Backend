package com.alameendev.ActingDriver.job.dto;


import com.alameendev.ActingDriver.job.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobUpdateDTO {
    private String title;
    private String description;
    private String requirements;
    private String pickupLocation;
    private String dropOffLocation;
    private String date;
    private String time;
    private JobStatus jobStatus;
}
