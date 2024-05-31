package com.alameendev.ActingDriver.filestorage.controller;


import com.alameendev.ActingDriver.filestorage.dto.FileUploadSuccessResponseDTO;
import com.alameendev.ActingDriver.filestorage.service.FileStorageService;
import com.alameendev.ActingDriver.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/file/")
@RequiredArgsConstructor
public class FileStorageController {

    private final FileStorageService fIleStorageService;
    private final UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadSuccessResponseDTO> uploadProfileImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String email = request.getUserPrincipal().getName();
        String fileName = fIleStorageService.uploadFile(file, email);

        return ResponseEntity.ok(FileUploadSuccessResponseDTO.builder().isUploaded(true).filePath(fileName).build());
    }
}
