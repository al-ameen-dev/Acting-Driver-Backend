package com.alameendev.ActingDriver.filestorage.service;

import com.alameendev.ActingDriver.exceptions.filestorage.FileIsEmptyException;
import com.alameendev.ActingDriver.exceptions.filestorage.FileStorageException;
import com.alameendev.ActingDriver.exceptions.filestorage.ProfileImageNotFoundException;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final UserService userService;

    private Path storageLocation;

    private Path fileLocation;

    private void initialize(String location){
        storageLocation = Paths.get(location).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public void uploadFile(String fileName, MultipartFile file, String uploadLocation) {
        initialize(uploadLocation);
        String oldFile = null;
        if (file.isEmpty()) {
            throw new FileIsEmptyException("Select and upload a image file!");
        }

        // Copy file to the target location (Replacing existing file with the same name)
        fileLocation = storageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), fileLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileResponse getFile(String fileName, String uploadLocation) {
        initialize(uploadLocation);
        byte[] imageData;
        File imageFile = new File(storageLocation.resolve(fileName).toUri());
        if (!imageFile.exists()) {
            throw new ProfileImageNotFoundException(fileName);
        }
        try {
            imageData = Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileResponse fileResponse = FileResponse.builder()
                .fileData(new ByteArrayResource(imageData))
                .fileName(fileName)
                .build();
        return fileResponse;
    }

    @Override
    public void deleteFile(String fileName, String uploadLocation, String dummyProfile) {
        File profileImage = new File(storageLocation.resolve(fileName).toUri());
        if (profileImage.exists() && !profileImage.getName().equals(dummyProfile)) {
            profileImage.delete();
        }
    }
}
