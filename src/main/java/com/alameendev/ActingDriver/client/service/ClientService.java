package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.client.dto.CarResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.client.entity.Car;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.user.entity.User;

import java.util.List;

public interface ClientService {


    void saveClient(Client client);

    ClientProfileResponseDTO retrieveClientById(Long id);

    Client retrieveClientByUser(User user);

    List<Client> retriveAllClients();

    ClientProfileUpdateDTO updateProfile(ClientProfileUpdateDTO body);

    CarResponseDTO addCar(Car body);
}
