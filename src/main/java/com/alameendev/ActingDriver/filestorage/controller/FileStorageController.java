package com.alameendev.ActingDriver.filestorage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/file/")
@RequiredArgsConstructor
public class FileStorageController {

//    private final FileStorageService fileStorageService;
//    @Value("${file.profile-upload-dir}")
    private String uploadLocation;

//    @Operation(summary = "API endpoint for uploading the image using POST Request")
//    @PostMapping("/profile-image")
//    public ResponseEntity<ByteArrayResource> uploadProfileImage(@RequestParam("file") MultipartFile file) throws IOException {
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(fileStorageService.uploadFile(file,uploadLocation));
//    }
//
//    @Operation(summary = "API endpoint for get the image using GET Request")
//    @GetMapping("/profile-image")
//    public ResponseEntity<ByteArrayResource> getProfileImage(HttpServletRequest request){
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(fileStorageService.getFile(uploadLocation));
//    }
//
//    @Operation(summary = "API endpoint for Deleting image using DELETE Request")
//    @DeleteMapping("/profile-image")
//    public ResponseEntity<ByteArrayResource> deleteProfileImage(HttpServletRequest request){
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(fileStorageService.deleteFile(uploadLocation));
//
//    }
}
