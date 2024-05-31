package com.alameendev.ActingDriver.actor.controller;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.actor.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping("")
    public ResponseEntity<List<Actor>> getAllActors(){
        return ResponseEntity.ok(actorService.retrieveAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Long id){
        return ResponseEntity.ok(actorService.retrieveActorById(id));
    }

    @PostMapping("/{id}/update-profile")
    public ResponseEntity<ActorProfileResponseDTO> updateProfile(@PathVariable Long id, @RequestBody ActorProfileUpdateDTO body){
        return ResponseEntity.ok(actorService.updateProfileWithId(id,body));
    }

    @PostMapping("/{id}/update-availability")
    public ResponseEntity<AvailabilityStatus> updateAvailability(@PathVariable Long id,@RequestParam AvailabilityStatus status){
        return ResponseEntity.ok(actorService.updateAvailabilityStatusWithId(id,status));
    }
}
