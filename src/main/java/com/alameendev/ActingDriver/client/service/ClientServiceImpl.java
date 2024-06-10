package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.repository.ClientRepository;
import com.alameendev.ActingDriver.exceptions.client.ClientNotFoundException;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.filestorage.service.FileService;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final FileService fileService;

    @Value("${file.actor-profile-upload-dir}")
    private String uploadLocation;
    @Value("${file.dummyProfile}")
    private String dummyProfile;

    @Value("${file.dummyImage-dir}")
    private String dummyLocation;

    @Override
    public List<Client> retriveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientProfileResponseDTO updateProfile(ClientProfileUpdateDTO body) {
        User user = userService.getUser();
        Client client = clientRepository.findByUser(user).orElseThrow(()->new ClientNotFoundException(user.getUserId()));
        client.setAddress(body.getAddress());
        client.setPhone(body.getPhone());
        client.setName(body.getName());
        clientRepository.save(client);
        return modelMapper.map(client,ClientProfileResponseDTO.class);
    }

    @Override
    public ClientProfileResponseDTO updateProfileByClientId(Long id, ClientProfileUpdateDTO body) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
        client.setAddress(body.getAddress());
        client.setPhone(body.getPhone());
        client.setName(body.getName());
        clientRepository.save(client);
        return modelMapper.map(client,ClientProfileResponseDTO.class);
    }

    @Override
    public CarDTO addCarByClientId(Long id,Car body) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
        Car car = Car.builder()
                .carNumber(body.getCarNumber())
                .client(client)
                .model(body.getModel()).build();
        clientRepository.save(client);
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public FileResponse getProfileImageByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException(id));
        if(!client.getProfilePictureName().equals(dummyProfile)){
            return fileService.getFile(client.getProfilePictureName(),uploadLocation);
        }
        return fileService.getFile(dummyProfile,uploadLocation);
    }

    @Override
    public FileResponse uploadProfileImageByClientId(Long id, MultipartFile file) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = client.getUser().getEmail()+ "-" + localDateTime.getYear() + localDateTime.getMonthValue() + localDateTime.getDayOfMonth() +
                localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond() + "-nano" +
                localDateTime.getNano() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        fileService.uploadFile(fileName,file,uploadLocation);
        String oldFile = client.getProfilePictureName();
        if(!oldFile.equals(dummyProfile)){
            fileService.deleteFile(oldFile,uploadLocation,dummyProfile);
        }
        client.setProfilePictureName(fileName);
        clientRepository.save(client);
        return fileService.getFile(fileName,uploadLocation);
    }

    @Override
    public FileResponse deleteProfileImageWithClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
        String fileName = client.getProfilePictureName();
        if(!fileName.equals(dummyProfile)){
            fileService.deleteFile(fileName,uploadLocation,dummyProfile);
        }
        client.setProfilePictureName(dummyProfile);
        clientRepository.save(client);
        return fileService.getFile(dummyProfile,dummyLocation);
    }

    @Override
    public CarDTO addCar(Car body) {
        User user = userService.getUser();
        Client client = clientRepository.findByUser(user).orElseThrow(()-> new ClientNotFoundException(user.getUserId()));
        Car car = Car.builder().carNumber(body.getCarNumber())
                        .client(client)
                        .model(body.getModel()).build();
        client.addCar(car);
        clientRepository.save(client);
        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClient() {
        User user = userService.getUser();
        return clientRepository.findByUser(user).orElseThrow(()-> new ClientNotFoundException(user.getUserId()));
    }

    @Override
    public ClientProfileResponseDTO retrieveClientProfileById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException(id));
        return modelMapper.map(client,ClientProfileResponseDTO.class);
    }

    @Override
    public Client retrieveClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
    }

    @Override
    public Client retrieveClientByUser(User user) {
        return clientRepository.findByUser(user).orElseThrow(()->new ClientNotFoundException(user.getUserId()));
    }


}
