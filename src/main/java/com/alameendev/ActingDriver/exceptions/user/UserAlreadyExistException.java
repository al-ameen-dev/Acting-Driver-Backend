package com.alameendev.ActingDriver.exceptions.user;

public class UserAlreadyExistException extends IllegalArgumentException{

    public UserAlreadyExistException(String email){
        super("The user with email "+email+" already exists");
    }
}
