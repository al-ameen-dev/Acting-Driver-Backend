package com.alameendev.ActingDriver.filestorage.service;

import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.service.ActorService;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.fileupload.FileIsEmptyException;
import com.alameendev.ActingDriver.exceptions.fileupload.ProfileImageNotFoundException;
import com.alameendev.ActingDriver.user.entity.Role;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final UserService userService;
    private final ActorService actorService;
    private final ClientService clientService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Value("${file.dummyProfile}")
    private String dummyProfile;

    @Override
    public String uploadFile(MultipartFile file,String email){
        User user = userService.getUserByEmail(email);
        if(file.isEmpty()){
            throw new FileIsEmptyException("Select and upload a image file!");
        }

        String fileName = SecurityContextHolder.getContext().getAuthentication().getName()+file.getOriginalFilename();
        File uploadedFile = new File(uploadPath,fileName);

        try {
            file.transferTo(uploadedFile);
            Role role = user.getRole();
            if(role.equals(Role.ACTOR)){
                Actor actor = actorService.retrieveActorByUser(user);
                actor.setProfilePictureUrl(uploadedFile.getAbsolutePath());
            }else if(role.equals(Role.CLIENT)){
                Client client = clientService.retrieveClientByUser(user);
                client.setProfilePictureUrl(uploadedFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    @Override
    public byte[] getFile(String email) {
        User user = userService.getUserByEmail(email);
        Role role = user.getRole();
        String imagePath = null;
        byte[] imageData;
        if(role.equals(Role.CLIENT)){
            Client client = clientService.retrieveClientByUser(user);
            imagePath = client.getProfilePictureUrl();
        }else if(role.equals(Role.ACTOR)){
            Actor actor = actorService.retrieveActorByUser(user);
            imagePath = actor.getProfilePictureUrl();
        }
        File imageFile = new File(imagePath);
        if(!imageFile.exists()){
            throw new ProfileImageNotFoundException(imagePath);
        }
        try {
            imageData = Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imageData;
    }

    @Override
    public byte[] deleteFile(String email) {
        User user = userService.getUserByEmail(email);
        Role role = user.getRole();
        String imagePath = null;
        if(role.equals(Role.ACTOR)){
            Actor actor = actorService.retrieveActorByUser(user);
            imagePath = actor.getProfilePictureUrl();
            actor.setProfilePictureUrl(uploadPath+dummyProfile);
        }else if(role.equals(Role.CLIENT)){
            Client client = clientService.retrieveClientByUser(user);
            imagePath = client.getProfilePictureUrl();
            client.setProfilePictureUrl(uploadPath+dummyProfile);
        }
        File profileImage = new File(imagePath);
        if(profileImage.exists() && !profileImage.getName().equals(dummyProfile)) {
            profileImage.delete();
        }
        return getFile(email);
    }


}
