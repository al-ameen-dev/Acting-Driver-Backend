package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.dto.ClientProfileResponseDTO;
import com.alameendev.ActingDriver.client.dto.ClientProfileUpdateDTO;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.repository.ClientRepository;
import com.alameendev.ActingDriver.exceptions.client.ClientNotFoundException;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    //private final CarService carService;

    @Override
    public List<Client> retriveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientProfileUpdateDTO updateProfile(ClientProfileUpdateDTO body) {
        User user = userService.getUser();
        Client client = clientRepository.findByUser(user).orElseThrow(()->new ClientNotFoundException(user.getUserId()));
        client.setAddress(body.getAddress());
        client.setPhone(body.getPhone());
        client.setName(body.getName());
        clientRepository.save(client);
        return body;
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
