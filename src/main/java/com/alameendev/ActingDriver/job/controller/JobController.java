package com.alameendev.ActingDriver.job.controller;

import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.dto.JobUpdateDTO;
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

    @Operation(summary = "Api endpoint for retrieving jobs by pagination using GET Request. Query parameters ?page='value'&&size='value'")
    @GetMapping("/paging")
    public ResponseEntity<List<JobResponseDTO>> getJobsByPaging(@RequestParam Integer page,@RequestParam Integer size){
        return ResponseEntity.ok(jobService.retrieveAllJobByPaging(page,size));
    }

    @Operation(summary = "Api endpoint for retrieving all available job using GET Request")
    @GetMapping("")
    public ResponseEntity<List<JobResponseDTO>> getAllJobs(){
        return ResponseEntity.ok(jobService.retriveAllJobs());
    }

    @Operation(summary = "Api endpoint for creating the Job using POST Request")
    @PostMapping("")
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobResponseDTO body){
        return ResponseEntity.ok(jobService.createJob(body));
    }

    @Operation(summary = "Api endpoint for creating the Job by Client Id using POST Request")
    @PostMapping("{id}")
    public ResponseEntity<JobResponseDTO> createJob(@PathVariable Long id,@RequestBody JobResponseDTO body){
        return ResponseEntity.ok(jobService.createJobByClientId(id,body));
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
