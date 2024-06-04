package com.alameendev.ActingDriver.jwtauthentication.controller;

import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationRequest;
import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationResponse;
import com.alameendev.ActingDriver.jwtauthentication.dto.RegisterRequest;
import com.alameendev.ActingDriver.jwtauthentication.service.JWTAuthenticationService;
import com.alameendev.ActingDriver.user.dto.PasswordForgetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetSuccessDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class JWTAuthenticationController {

    private final JWTAuthenticationService jwtAuthenticationService;

    @Operation(summary = "API endpoint for Registering the user using POST Request")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> reigster(
            @RequestBody RegisterRequest request
    )
    {
       return ResponseEntity.ok(jwtAuthenticationService.register(request));
    }

    @Operation(summary = "API endpoint for retrieving the authToken using POST Request")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(jwtAuthenticationService.authenticate(request));
    }

    @Operation(summary = "API endpoint for Forgot password using POST Request")
    @PostMapping("/forgotpassword")
    public ResponseEntity<PasswordResetSuccessDTO> forgetPassword(@RequestBody PasswordForgetDTO body, HttpServletRequest request){
        String email = request.getUserPrincipal().getName();
        return ResponseEntity.ok(jwtAuthenticationService.forgetPasswordForUser(email,body));
    }

    @Operation(summary = "API endpoint for Reset password using POST Request")
    @PostMapping("/resetpassword")
    public ResponseEntity<PasswordResetSuccessDTO> resetPassword(@RequestBody PasswordResetDTO body,HttpServletRequest request){
        String email = request.getUserPrincipal().getName();
        return ResponseEntity.ok(jwtAuthenticationService.resetPasswordForUser(email,body));
    }
}
