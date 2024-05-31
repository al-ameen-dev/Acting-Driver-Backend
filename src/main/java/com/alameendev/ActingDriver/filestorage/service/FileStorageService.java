package com.alameendev.ActingDriver.filestorage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String uploadFile(MultipartFile file, String email) throws IOException;
    byte[] getFile(String email);

    byte[] deleteFile(String email);
}
