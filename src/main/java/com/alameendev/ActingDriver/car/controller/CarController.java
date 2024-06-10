package com.alameendev.ActingDriver.car.controller;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.service.CarService;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
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
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final ContentTypeRetriever contentTypeRetriever;

    @Operation(summary = "API endpoint for retrieving all Cars using GET Request")
    @GetMapping("")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(carService.retrieveAllCars());
    }

    @Operation(summary = "API endpoint for retrieving the car for the client by Client Id using GET Request")
    @GetMapping("/{id}/client")
    public ResponseEntity<List<CarDTO>> getCarsForTheClientById(@PathVariable Long id){
        return ResponseEntity.ok(carService.retrieveCarsForTheClientById(id));
    }

    @Operation(summary = "API endpoint for retrieving the cars for the current Logged in Client using GET Request")
    @GetMapping("/client")
    public ResponseEntity<List<CarDTO>> getCarsForTheClient(){
        return ResponseEntity.ok(carService.retrieveCarsForTheClient());
    }

    @Operation(summary = "API endpoint for retrieving the Car by Car Id using GET Request")
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.retrieveCarById(id));
    }

    @Operation(summary = "API endpoint for creating or adding Car for the current Logged in Client using POST Request")
    @PostMapping("")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO body){
        return ResponseEntity.ok(carService.createCar(body));
    }

    @Operation(summary = "API endpoint for creating or adding Car for the Client by Client Id using POST Request")
    @PostMapping("/{id}/client")
    public ResponseEntity<CarDTO> createCarByClientId(@PathVariable Long id,@RequestBody CarDTO body){
        return ResponseEntity.ok(carService.createCarByClientId(id,body));
    }

    @Operation(summary = "API endpoint for Updating the Car information by id using PUT Request")
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id,@RequestBody CarDTO body){
        return ResponseEntity.ok(carService.updateCarById(id,body));
    }

    @Operation(summary = "API endpoint for Deleting the car by id using DELETE Request")
    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Long id){
        carService.deleteCarById(id);
    }

    @Operation(summary = "API endpoint for Retrieving the car Image by CarId using GET Request")
    @GetMapping("/{id}/image")
    public ResponseEntity<ByteArrayResource> getCarImageById(@PathVariable Long id){
        FileResponse fileResponse = carService.getCarImageByCarId(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "API endpoint for Uploading the car Image by CarId using UPLOAD Request")
    @PostMapping("/{id}/image")
    public ResponseEntity<ByteArrayResource> uploadCarImageById(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        FileResponse fileResponse = carService.uploadCarImageByCarId(id,file);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "API endpoint for deleting the car Image by CarId using Delete Request")
    @DeleteMapping("{id}/image")
    public ResponseEntity<ByteArrayResource> deleteCarImageById(@PathVariable Long id){
        FileResponse fileResponse = carService.getCarImageByCarId(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

}

