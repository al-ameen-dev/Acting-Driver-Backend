package com.alameendev.ActingDriver.exceptions.actor;

public class ActorNotFoundException extends IllegalArgumentException{

    public ActorNotFoundException(Long id){
        super("Actor with id"+id+" not found!");
    }
}
