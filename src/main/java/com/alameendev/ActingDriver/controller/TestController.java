package com.alameendev.ActingDriver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/testing")
public class TestController {
    @GetMapping("")
    public String helloWorld(){
        return "Hello, World";
    }

}
