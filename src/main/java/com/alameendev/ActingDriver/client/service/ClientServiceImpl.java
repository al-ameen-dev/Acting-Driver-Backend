package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.repository.ClientRepository;
import com.alameendev.ActingDriver.exceptions.client.ClientNotFoundException;
import com.alameendev.ActingDriver.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client retrieveClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(()->new ClientNotFoundException(id));
    }

    @Override
    public Client retrieveClientByUser(User user) {
        return clientRepository.findById(user.getUserId()).orElseThrow(()->new ClientNotFoundException(user.getUserId()));
    }
}
