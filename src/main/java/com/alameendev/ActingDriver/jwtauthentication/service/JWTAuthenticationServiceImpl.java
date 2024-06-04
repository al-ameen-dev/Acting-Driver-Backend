package com.alameendev.ActingDriver.jwtauthentication.service;

import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.service.ActorService;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationRequest;
import com.alameendev.ActingDriver.jwtauthentication.dto.AuthenticationResponse;
import com.alameendev.ActingDriver.jwtauthentication.dto.RegisterRequest;
import com.alameendev.ActingDriver.user.dto.PasswordForgetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetDTO;
import com.alameendev.ActingDriver.user.dto.PasswordResetSuccessDTO;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTAuthenticationServiceImpl implements JWTAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final UserService userService;
    private final ActorService actorService;
    private final ClientService clientService;

    @Value("${file.dummyProfile}")
    private String dummyProfile;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName((request.getLastName()))
                .email(request.getEmail())
                .role(request.getRole())
                .gender(request.getGender())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userService.saveUser(user);
        createProfile(user);
        String token = jwtService.generateTokenForUser(user);
        return AuthenticationResponse.builder().token(token)
                .build();
    }

    private void createProfile(User user){
        switch (user.getRole()){
            case ACTOR -> {
                Actor actor = Actor.builder().user(user).name(user.getFirstName()+" "+user.getLastName())
                        .profilePictureName(dummyProfile).build();
                actorService.saveActor(actor);
            }
            case CLIENT -> {
                Client client = Client.builder().user(user).name(user.getFirstName()+" "+user.getLastName())
                        .profilePictureName(dummyProfile).build();
                clientService.saveClient(client);
            }
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.getUserByEmail(request.getEmail());
        String token = jwtService.generateTokenForUser(user);
        return AuthenticationResponse.builder().token(token)
                .build();
    }

    @Override
    public PasswordResetSuccessDTO forgetPasswordForUser(String email, PasswordForgetDTO passwordForgetDTO) {
        User user = userService.getUserByEmail(email);
        user.setPassword(passwordEncoder.encode(passwordForgetDTO.getNewPassword()));
        userService.saveUser(user);
        return PasswordResetSuccessDTO.builder().message("Password was successfully reset!").success(true).build();
    }

    @Override
    public PasswordResetSuccessDTO resetPasswordForUser(String email, PasswordResetDTO passwordResetDTO) {
        User user = userService.getUserByEmail(email);
        if(passwordEncoder.matches(passwordResetDTO.getOldPassword(),user.getPassword())){
            user.setPassword(passwordEncoder.encode(passwordResetDTO.getNewPassword()));
            userService.saveUser(user);
            return PasswordResetSuccessDTO.builder().message("Password was successfully reset!").success(true).build();
        }
        return PasswordResetSuccessDTO.builder().message("Failed to reset password!. old password doesn't match!").success(false).build();
    }


}
