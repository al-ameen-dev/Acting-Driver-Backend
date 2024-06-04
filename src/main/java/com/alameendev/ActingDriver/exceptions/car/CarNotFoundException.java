package com.alameendev.ActingDriver.exceptions.car;

public class CarNotFoundException extends IllegalArgumentException{
    public CarNotFoundException(Long id){
        super("The car with Id "+id+" is not found!");
    }
}
