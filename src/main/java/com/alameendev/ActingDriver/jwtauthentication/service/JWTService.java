package com.alameendev.ActingDriver.jwtauthentication.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JWTService {

    String extractUserName(String jwtToken);
    String generateTokenForUser(UserDetails userDetails);
    boolean isTokenValid(String jwtToken, UserDetails userDetails);
    <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver);
}
