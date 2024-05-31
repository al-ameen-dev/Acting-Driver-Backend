package com.alameendev.ActingDriver.exceptions.client;

public class ClientNotFoundException extends IllegalArgumentException{

    public ClientNotFoundException(Long id){
        super("The client with Id "+id+" not found!");
    }
}
