package com.alameendev.ActingDriver.filestorage.service;

import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.service.ActorService;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.filestorage.FileIsEmptyException;
import com.alameendev.ActingDriver.exceptions.filestorage.FileStorageException;
import com.alameendev.ActingDriver.exceptions.filestorage.ProfileImageNotFoundException;
import com.alameendev.ActingDriver.user.entity.Role;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final UserService userService;
    private final ActorService actorService;
    private final ClientService clientService;
    private final Path root = Paths.get("uploads");

    @Value("${file.upload-dir}")
    private String uploadLocation;

    private Path storageLocation;

    private Path fileLocation;

    @Value("${file.dummyProfile}")
    private String dummyProfile;

    @PostConstruct
    public void initialize() {
        storageLocation = Paths.get(uploadLocation).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public ByteArrayResource uploadFile(MultipartFile file) throws IOException {
        String oldFile = null;
        if (file.isEmpty()) {
            throw new FileIsEmptyException("Select and upload a image file!");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        String fileName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()+"-"+localDateTime.getYear()+localDateTime.getMonthValue()+localDateTime.getDayOfMonth()+
                localDateTime.getHour()+localDateTime.getMinute()+localDateTime.getSecond()+"-nano"+
                localDateTime.getNano()+"."+StringUtils.getFilenameExtension(file.getOriginalFilename());

        // Copy file to the target location (Replacing existing file with the same name)
        fileLocation = storageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), fileLocation, StandardCopyOption.REPLACE_EXISTING);
        Role role = user.getRole();
        if (role.equals(Role.ACTOR)) {
            Actor actor = actorService.retrieveActorByUser(user);
            oldFile = actor.getProfilePictureName();
            actor.setProfilePictureName(fileName);
            actorService.saveActor(actor);
        } else if (role.equals(Role.CLIENT)) {
            System.out.println("client");
            Client client = clientService.retrieveClientByUser(user);
            oldFile = client.getProfilePictureName();
            client.setProfilePictureName(fileName);
            clientService.saveClient(client);
        }
        File old = new File(storageLocation.resolve(oldFile).toUri());
        if (old.exists() && !oldFile.equals(dummyProfile)) {
            old.delete();
        }
        return new ByteArrayResource(Files.readAllBytes(fileLocation));
    }

    @Override
    public ByteArrayResource getFile() {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Role role = user.getRole();
        String fileName = null;
        byte[] imageData;
        if (role.equals(Role.CLIENT)) {
            Client client = clientService.retrieveClientByUser(user);
            fileName = client.getProfilePictureName();
        } else if (role.equals(Role.ACTOR)) {
            Actor actor = actorService.retrieveActorByUser(user);
            fileName = actor.getProfilePictureName();
        }
        File imageFile = new File(storageLocation.resolve(fileName).toUri());
        if (!imageFile.exists()) {
            throw new ProfileImageNotFoundException(fileName);
        }
        try {
            imageData = Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
}
        return new ByteArrayResource(imageData);
                }

@Override
    public ByteArrayResource deleteFile() {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Role role = user.getRole();
        String fileName = null;
        if (role.equals(Role.ACTOR)) {
            Actor actor = actorService.retrieveActorByUser(user);
            fileName = actor.getProfilePictureName();
            actor.setProfilePictureName(dummyProfile);
            actorService.saveActor(actor);
        } else if (role.equals(Role.CLIENT)) {
            Client client = clientService.retrieveClientByUser(user);
            fileName = client.getProfilePictureName();
            client.setProfilePictureName(dummyProfile);
            clientService.saveClient(client);
        }
        File profileImage = new File(storageLocation.resolve(fileName).toUri());
        if (profileImage.exists() && !profileImage.getName().equals(dummyProfile)) {
            profileImage.delete();
        }
        return getFile();
    }

}
