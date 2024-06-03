package com.alameendev.ActingDriver.exceptions.filestorage;

public class FileIsEmptyException extends IllegalArgumentException{

    public FileIsEmptyException(String message){
        super(message);
    }
}
