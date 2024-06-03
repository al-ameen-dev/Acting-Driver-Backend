package com.alameendev.ActingDriver.client.controller;

import com.alameendev.ActingDriver.client.dto.CarResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.client.entity.Car;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.entity.Job;
import com.alameendev.ActingDriver.job.service.JobService;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final JobService jobService;
    private final UserService userService;

    @Operation(summary = "API endpoint for Retrieving all Clients using GET Request")
    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.retriveAllClients());
    }

    @Operation(summary = "API endpoint for Retrieving specific Client by Id using GET Request")
    @GetMapping("/{id}")
    public ResponseEntity<ClientProfileResponseDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.retrieveClientById(id));
    }

    @Operation(summary = "API endpoint for updating the client profile using POST Request")
    @PutMapping("/update-profile")
    public ResponseEntity<ClientProfileUpdateDTO> updateClientProfile(@RequestBody ClientProfileUpdateDTO body){
        return ResponseEntity.ok(clientService.updateProfile(body));

    }

    @Operation(summary = "API endpoint for add car to the specific client using POST Request")
    @PostMapping("/add-car")
    public ResponseEntity<CarResponseDTO> addCar(@RequestBody Car body){
        return ResponseEntity.ok(clientService.addCar(body));
    }

    @Operation(summary = "API endpoint for that allows to create a new job listing using POST Request")
    @PostMapping("/create-job")
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody Job body){
        return ResponseEntity.ok(jobService.createJob(body));
    }

    @Operation(summary = "API endpoint for retrieving the Job for the speicific Client")
    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<JobResponseDTO>> getJobsForTheClient(){
        User user = userService.getUser();
        Client client = clientService.retrieveClientByUser(user);
        return ResponseEntity.ok(jobService.retriveAllJobForClient(client));
    }
}
