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
    private String location;

    //@JsonFormat(pattern = "dd-mm-yyyy")
    private String date;

    //@JsonFormat(pattern = "HH:mm:ss")
    private String time;
    private JobStatus jobStatus;
}
