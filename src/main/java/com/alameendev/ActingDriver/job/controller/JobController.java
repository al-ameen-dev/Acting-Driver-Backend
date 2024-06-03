package com.alameendev.ActingDriver.job.controller;

import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.dto.JobUpdateDTO;
import com.alameendev.ActingDriver.job.entity.Job;
import com.alameendev.ActingDriver.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    @Operation(summary = "Api endpoint for retrieving all available job using GET Request")
    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.ok(jobService.retriveAllJobs());
    }

    @Operation(summary = "Api endpoint for creating the Job using POST Request")
    @PostMapping("/create-job")
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody Job body){
        return ResponseEntity.ok(jobService.createJob(body));
    }

    @Operation(summary = "Api endpoint for specific job by Id using GET Request")
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id){
        return ResponseEntity.ok(jobService.retriveJobById(id));
    }

    @Operation(summary = "Api endpoint for updating the job by id using PUT Request")
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id, @RequestBody JobUpdateDTO body){
        return ResponseEntity.ok(jobService.updateJobById(id,body));
    }

    @Operation(summary = "Api endpoint for deleting the Job by id using Delete Request")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable Long id){
        jobService.deleteJobById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
