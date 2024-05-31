package com.alameendev.ActingDriver.exceptions.user;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(String email){
        super("User with email "+email+" not found!");
    }

    public UserNotFoundException(Long userId){
        super("User with user id"+userId+" not found!");
    }

}
