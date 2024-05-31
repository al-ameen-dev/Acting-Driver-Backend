package com.alameendev.ActingDriver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/hello")
public class DemoController {


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("")
    public String hello(HttpServletRequest request)
    {
        return "Hello "+request.getUserPrincipal().getName();
    }

    @GetMapping("/test")
    public Principal getPrincipal(HttpServletRequest request){
        return request.getUserPrincipal();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin()
    {
        return "only admin can access this";
    }

    @GetMapping("/user")
    public String user()
    {
        return "only user can acces this";
    }
}
