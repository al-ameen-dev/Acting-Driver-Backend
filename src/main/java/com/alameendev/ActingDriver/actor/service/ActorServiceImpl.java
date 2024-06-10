package com.alameendev.ActingDriver.actor.service;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.actor.repository.ActorRepository;
import com.alameendev.ActingDriver.exceptions.actor.ActorNotFoundException;
import com.alameendev.ActingDriver.exceptions.user.UserNotFoundException;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.filestorage.service.FileService;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    //private final FileStorageService fileStorageService;
    private final FileService fileService;

    @Value("${file.actor-profile-upload-dir}")
    private String uploadLocation;
    @Value("${file.dummyProfile}")
    private String dummyProfile;

    @Value("${file.dummyImage-dir}")
    private String dummyLocation;

    @Override
    public List<Actor> retrieveAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor retrieveActorById(Long id) {
        return actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException(id));
    }

    @Override
    public Actor retrieveActorByUser(User user) {
        return actorRepository.findByUser(user).orElseThrow(()->new ActorNotFoundException(user.getUserId()));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public ActorProfileResponseDTO updateProfile(ActorProfileUpdateDTO body) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email);
        Actor actor = actorRepository.findByUser(user).orElseThrow(()->new UserNotFoundException(user.getUserId()));
        actor.setAvailabilityStatus(body.getAvailabilityStatus());
        actor.setCredentials(body.getCredentials());
        actor.setDriverExperience(body.getDriverExperience());
        actor.setName(body.getName());
        actor.setPhone(body.getPhone());
        actor.setBiography(body.getBiography());
        actorRepository.save(actor);
        return modelMapper.map(actor,ActorProfileResponseDTO.class);
    }

    @Override
    public ActorProfileResponseDTO updateProfileByActorId(Long id, ActorProfileUpdateDTO body) {
        Actor actor = actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException(id));
        actor.setAvailabilityStatus(body.getAvailabilityStatus());
        actor.setName(body.getName());
        actor.setDriverExperience(body.getDriverExperience());
        actor.setPhone(body.getPhone());
        actor.setBiography(body.getBiography());
        actor.setCredentials(body.getCredentials());
        actorRepository.save(actor);
        return modelMapper.map(actor,ActorProfileResponseDTO.class);
    }

    @Override
    public AvailabilityStatus updateAvailabilityStatus(AvailabilityStatus status) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email);
        Actor actor = actorRepository.findByUser(user).orElseThrow(()->new UserNotFoundException(user.getUserId()));
        actor.setAvailabilityStatus(status);
        actorRepository.save(actor);
        return status;
    }

    @Override
    public ActorProfileResponseDTO updateAvailabilityStatusByActorId(Long id,AvailabilityStatus status) {
        Actor actor = actorRepository.findById(id).orElseThrow(()-> new ActorNotFoundException(id));
        actor.setAvailabilityStatus(status);
        actorRepository.save(actor);
        return modelMapper.map(actor,ActorProfileResponseDTO.class);
    }

    @Override
    public FileResponse getProfileImage() {
        User user = userService.getUser();
        Actor actor = actorRepository.findByUser(user).orElseThrow(()->new UserNotFoundException(user.getUserId()));
        if(!actor.getProfilePictureName().equals(dummyProfile)){
            return fileService.getFile(actor.getProfilePictureName(),uploadLocation);
        }
        return fileService.getFile(dummyProfile,dummyLocation);
    }

    @Override
    public FileResponse getProfileImageForActorById(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(()-> new ActorNotFoundException(id));
        if(!actor.getProfilePictureName().equals(dummyProfile)){
            return fileService.getFile(actor.getProfilePictureName(),uploadLocation);
        }
        return fileService.getFile(dummyProfile,dummyLocation);
    }

    @Override
    public FileResponse uploadProfileImage(MultipartFile file){
        User user = userService.getUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = user.getEmail()+ "-" + localDateTime.getYear() + localDateTime.getMonthValue() + localDateTime.getDayOfMonth() +
                localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond() + "-nano" +
                localDateTime.getNano() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        fileService.uploadFile(fileName,file,uploadLocation);
        Actor actor = actorRepository.findByUser(user).orElseThrow(()->new ActorNotFoundException(user.getUserId()));
        String oldFile = actor.getProfilePictureName();
        if (!oldFile.equals(dummyProfile)) {
            fileService.deleteFile(oldFile,uploadLocation,dummyProfile);
        }
        actor.setProfilePictureName(fileName);
        actorRepository.save(actor);
        return  fileService.getFile(fileName,uploadLocation);
    }

    @Override
    public FileResponse uploadProfileImageWithActorId(Long id, MultipartFile file) {
        Actor actor = actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException(id));
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = actor.getUser().getEmail()+"-" + localDateTime.getYear() + localDateTime.getMonthValue() + localDateTime.getDayOfMonth() +
                localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond() + "-nano" +
                localDateTime.getNano() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        fileService.uploadFile(fileName,file,uploadLocation);
        String oldFile = actor.getProfilePictureName();
        if (!oldFile.equals(dummyProfile)) {
            fileService.deleteFile(oldFile,uploadLocation,dummyProfile);
        }
        actor.setProfilePictureName(fileName);
        actorRepository.save(actor);
        return  fileService.getFile(fileName,uploadLocation);
    }

    @Override
    public FileResponse deleteProfileImage(){
        User user = userService.getUser();
        Actor actor = actorRepository.findByUser(user).orElseThrow(()->new ActorNotFoundException(user.getUserId()));
        String fileName = actor.getProfilePictureName();
        if (!fileName.equals(dummyProfile)) {
            fileService.deleteFile(fileName,uploadLocation,dummyProfile);
        }
        actor.setProfilePictureName(dummyProfile);
        actorRepository.save(actor);
        return fileService.getFile(dummyProfile,dummyLocation);
    }

    @Override
    public FileResponse deleteProfileImageWithActorId(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(()-> new ActorNotFoundException(id));
        String fileName = actor.getProfilePictureName();
        if (!fileName.equals(dummyProfile)) {
            fileService.deleteFile(fileName,uploadLocation,dummyProfile);
        }
        actor.setProfilePictureName(dummyProfile);
        actorRepository.save(actor);
        return fileService.getFile(dummyProfile,dummyLocation);
    }

}
