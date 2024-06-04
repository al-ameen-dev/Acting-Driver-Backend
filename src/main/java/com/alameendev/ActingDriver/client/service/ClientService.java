package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.user.entity.User;

import java.util.List;

public interface ClientService {


    void saveClient(Client client);

    Client getClient();
    ClientProfileResponseDTO retrieveClientProfileById(Long id);

    Client retrieveClientById(Long id);
    Client retrieveClientByUser(User user);

    List<Client> retriveAllClients();

    ClientProfileUpdateDTO updateProfile(ClientProfileUpdateDTO body);

    CarDTO addCar(Car body);
}
