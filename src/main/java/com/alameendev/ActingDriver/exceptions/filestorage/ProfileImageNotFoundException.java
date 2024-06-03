package com.alameendev.ActingDriver.exceptions.filestorage;

public class ProfileImageNotFoundException extends IllegalArgumentException{

    public  ProfileImageNotFoundException(String name){
        super("The image with path "+name+" not exists!");
    }
}
