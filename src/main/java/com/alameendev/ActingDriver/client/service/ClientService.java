package com.alameendev.ActingDriver.client.service;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.user.entity.User;

public interface ClientService {

    void saveClient(Client client);

    Client retrieveClientById(Long id);

    Client retrieveClientByUser(User user);
}
