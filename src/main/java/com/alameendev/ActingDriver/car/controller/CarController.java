package com.alameendev.ActingDriver.car.controller;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

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
}
