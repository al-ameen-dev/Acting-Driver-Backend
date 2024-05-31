package com.alameendev.ActingDriver.exceptions.fileupload;

public class FileIsEmptyException extends IllegalArgumentException{

    public FileIsEmptyException(String message){
        super(message);
    }
}
