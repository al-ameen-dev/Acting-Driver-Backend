package com.alameendev.ActingDriver.filestorage.controller;


import com.alameendev.ActingDriver.filestorage.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/file/")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @Operation(summary = "API endpoint for uploading the image using POST Request")
    @PostMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> uploadProfileImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileStorageService.uploadFile(file));
    }

    @Operation(summary = "API endpoint for get the image using GET Request")
    @GetMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> getProfileImage(HttpServletRequest request){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileStorageService.getFile());
    }

    @Operation(summary = "API endpoint for Deleting image using DELETE Request")
    @DeleteMapping("/profile-image")
    public ResponseEntity<ByteArrayResource> deleteProfileImage(HttpServletRequest request){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileStorageService.deleteFile());

    }
}
