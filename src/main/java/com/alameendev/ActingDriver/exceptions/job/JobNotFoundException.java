package com.alameendev.ActingDriver.exceptions.job;

public class JobNotFoundException extends IllegalArgumentException{

    public JobNotFoundException(Long id) {
        super("The job with Id "+id+" not found");
    }
}
