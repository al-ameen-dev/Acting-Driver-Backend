package com.alameendev.ActingDriver.jwtauthentication.service;

import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationRequest;
import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationResponse;
import com.alameendev.ActingDriver.jwtauthentication.dto.RegisterRequest;
import com.alameendev.ActingDriver.user.dto.PasswordForgetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetSuccessDTO;

public interface JWTAuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    PasswordResetSuccessDTO forgetPasswordForUser(String email, PasswordForgetDTO passwordForgetDTO);
    PasswordResetSuccessDTO resetPasswordForUser(String email, PasswordResetDTO passwordResetDTO);
}
