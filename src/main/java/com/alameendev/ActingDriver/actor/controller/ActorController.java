package com.alameendev.ActingDriver.actor.controller;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.actor.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @Operation(summary = "API endpoint for Retrieving all actors using GET Request")
    @GetMapping("")
    public ResponseEntity<List<Actor>> getAllActors(){
        return ResponseEntity.ok(actorService.retrieveAllActors());
    }

    @Operation(summary = "API endpoint for Retrieving a single actor by id using GET Request")
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Long id){
        return ResponseEntity.ok(actorService.retrieveActorById(id));
    }

    @Operation(summary = "API endpoint for Updating the actors profile information by id using POST Request")
    @PutMapping("/update-profile")
    public ResponseEntity<ActorProfileResponseDTO> updateProfile(@RequestBody ActorProfileUpdateDTO body){
        return ResponseEntity.ok(actorService.updateProfile(body));
    }

    @Operation(summary = "API endpoint for only Updating the availability of the actor using POST Request")
    @PutMapping("/update-availability")
    public ResponseEntity<AvailabilityStatus> updateAvailability(@RequestBody AvailabilityStatus status){
        return ResponseEntity.ok(actorService.updateAvailabilityStatus(status));
    }
}
