package com.alameendev.ActingDriver.filestorage.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    ByteArrayResource uploadFile(MultipartFile file) throws IOException;
    ByteArrayResource getFile();

    ByteArrayResource deleteFile();
}
