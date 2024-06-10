package com.alameendev.ActingDriver.client.controller;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.job.dto.JobResponseDTO;
import com.alameendev.ActingDriver.job.service.JobService;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import com.alameendev.ActingDriver.utility.ContentTypeRetriever;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final JobService jobService;
    private final UserService userService;
    private final ContentTypeRetriever contentTypeRetriever;

    @Operation(summary = "API endpoint for Retrieving all Clients using GET Request")
    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.retriveAllClients());
    }

    @Operation(summary = "API endpoint for Retrieving specific Client by Id using GET Request")
    @GetMapping("/{id}")
    public ResponseEntity<ClientProfileResponseDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.retrieveClientProfileById(id));
    }

    @Operation(summary = "API endpoint for updating the client profile using PUT Request")
    @PutMapping("")
    public ResponseEntity<ClientProfileResponseDTO> updateClientProfile(@RequestBody ClientProfileUpdateDTO body){
        return ResponseEntity.ok(clientService.updateProfile(body));

    }

    @Operation(summary = "API endpoint for updating the client profile by Client Id using PUT Request")
    @PutMapping("/{id}")
    public ResponseEntity<ClientProfileResponseDTO> updateClientProfileById(@PathVariable Long id,@RequestBody ClientProfileUpdateDTO body){
        return ResponseEntity.ok(clientService.updateProfileByClientId(id,body));

    }

    @Operation(summary = "API endpoint for add car to the specific client using POST Request")
    @PostMapping("/add-car")
    public ResponseEntity<CarDTO> addCar(@RequestBody Car body){
        return ResponseEntity.ok(clientService.addCar(body));
    }

    @Operation(summary = "API endpoint for add car to the specific client By Client Id using POST Request")
    @PostMapping("/{id}/add-car")
    public ResponseEntity<CarDTO> addCarById(@PathVariable Long id,@RequestBody Car body){
        return ResponseEntity.ok(clientService.addCarByClientId(id,body));
    }

    @Operation(summary = "API endpoint for that allows to create a new job listing using POST Request")
    @PostMapping("/create-job")
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobResponseDTO body){
        return ResponseEntity.ok(jobService.createJob(body));
    }

    @Operation(summary = "API endpoint for that allows to create a new job listing by Client ID using POST Request")
    @PostMapping("/{id}/create-job")
    public ResponseEntity<JobResponseDTO> createJobUsingClientId(@PathVariable Long id,@RequestBody JobResponseDTO body){
        return ResponseEntity.ok(jobService.createJobByClientId(id,body));
    }

    @Operation(summary = "API endpoint for retrieving the Jobs for the current logged in Client using GET Request")
    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponseDTO>> getJobsForTheClient(){
        User user = userService.getUser();
        Client client = clientService.retrieveClientByUser(user);
        return ResponseEntity.ok(jobService.retriveAllJobForClient(client));
    }

    @Operation(summary = "API endpoint for retrieving Jobs for the Client by Client Id using GET Request")
    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<JobResponseDTO>> getJobsForTheClientUsingClientId(@PathVariable Long id){
        Client client = clientService.retrieveClientById(id);
        return ResponseEntity.ok(jobService.retriveAllJobForClient(client));
    }


    @Operation(summary = "API endpoint for retrieving the client profile image by client id using GET Request")
    @GetMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> getProfileImageById(@PathVariable Long id){
        FileResponse fileResponse = clientService.getProfileImageByClientId(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "API endpoint for uploading the image for the Client by Client Id using POST Request")
    @PostMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> uploadProfileImageById(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        FileResponse fileResponse = clientService.uploadProfileImageByClientId(id,file);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "API endpoint for deleting the profile image by Client Id using Delete Request")
    @DeleteMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> deleteProfileImageById(@PathVariable Long id){
        FileResponse fileResponse = clientService.deleteProfileImageWithClientId(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }
}
