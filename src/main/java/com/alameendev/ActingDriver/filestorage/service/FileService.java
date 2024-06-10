package com.alameendev.ActingDriver.filestorage.service;

import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void uploadFile(String fileName, MultipartFile file, String uploadLocation);

    FileResponse getFile(String fileName, String uploadLocation);

    void deleteFile(String fileName, String uploadLocation, String dummyProfile);
}
