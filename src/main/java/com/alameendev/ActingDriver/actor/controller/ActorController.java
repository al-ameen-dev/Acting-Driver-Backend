package com.alameendev.ActingDriver.actor.controller;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.actor.service.ActorService;
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
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;
    private final ContentTypeRetriever contentTypeRetriever;

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

    @Operation(summary = "API endpoint for Updating the actors profile information by id using POST Request")
    @PutMapping("/{id}/update-profile")
    public ResponseEntity<ActorProfileResponseDTO> updateProfileById(@PathVariable Long id,@RequestBody ActorProfileUpdateDTO body){
        return ResponseEntity.ok(actorService.updateProfileByActorId(id,body));
    }

    @Operation(summary = "API endpoint for only Updating the availability of the actor using POST Request")
    @PutMapping("/update-availability")
    public ResponseEntity<AvailabilityStatus> updateAvailability(@RequestBody AvailabilityStatus status){
        return ResponseEntity.ok(actorService.updateAvailabilityStatus(status));
    }

    @Operation(summary = "API endpoint for only Updating the availability of the actor by Id using POST Request")
    @PutMapping("/{id}/update-availability")
    public ResponseEntity<ActorProfileResponseDTO> updateAvailabilityById(@PathVariable Long id,@RequestBody AvailabilityStatus status){
        return ResponseEntity.ok(actorService.updateAvailabilityStatusByActorId(id,status));
    }

    @Operation(summary = "Api endpoint for retrieving the profile image for specific Actor by using GET Request")
    @GetMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> getProfileImage(){
        FileResponse fileResponse = actorService.getProfileImage();
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "Api endpoint for retrieving the profile image for specific Actor by Actor id using GET Request")
    @GetMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> getProfileImageById(@PathVariable Long id){
        FileResponse fileResponse = actorService.getProfileImageForActorById(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }


    @Operation(summary = "Api endpoint for uploading the profile image for the Logged in actor using POST request")
    @PostMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        FileResponse fileResponse = actorService.uploadProfileImage(file);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "Api endpoint for uploading the profile image for the Logged in actor using POST request")
    @PostMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> uploadProfileImageById(@PathVariable Long id,@RequestParam("file")MultipartFile file) {
        FileResponse fileResponse = actorService.uploadProfileImageWithActorId(id,file);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "Api endpoint for deleting the profile image for the Logged in actor using DELETE request")
    @DeleteMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> deleteProfileImage(){
        FileResponse fileResponse = actorService.deleteProfileImage();
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

    @Operation(summary = "Api endpoint for deleting the profile image for the Logged in actor using DELETE request")
    @DeleteMapping("/{id}/profile-image")
    public ResponseEntity<ByteArrayResource> deleteProfileImageByActorId(@PathVariable Long id){
        FileResponse fileResponse = actorService.deleteProfileImageWithActorId(id);
        MediaType contentType = contentTypeRetriever.getContentType(fileResponse.getFileName());
        return ResponseEntity.ok().contentType(contentType).body(fileResponse.getFileData());
    }

}
