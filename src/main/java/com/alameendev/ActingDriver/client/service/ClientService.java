package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientService {


    void saveClient(Client client);

    Client getClient();
    ClientProfileResponseDTO retrieveClientProfileById(Long id);

    Client retrieveClientById(Long id);
    Client retrieveClientByUser(User user);

    List<Client> retriveAllClients();

    ClientProfileResponseDTO updateProfile(ClientProfileUpdateDTO body);

    CarDTO addCar(Car body);

    ClientProfileResponseDTO updateProfileByClientId(Long id, ClientProfileUpdateDTO body);

    CarDTO addCarByClientId(Long id,Car body);

    FileResponse getProfileImageByClientId(Long id);

    FileResponse uploadProfileImageByClientId(Long id, MultipartFile file);

    FileResponse deleteProfileImageWithClientId(Long id);
}
