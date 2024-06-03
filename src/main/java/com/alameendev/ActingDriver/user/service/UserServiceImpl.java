package com.alameendev.ActingDriver.user.service;

import com.alameendev.ActingDriver.exceptions.user.UserAlreadyExistException;
import com.alameendev.ActingDriver.exceptions.user.UserNotFoundException;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @Override
    public User saveUser(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail()).orElse(User.builder().email("f").build());
        if(oldUser.getEmail().equals(user.getEmail())){
            throw new UserAlreadyExistException(user.getEmail());
        }
        return userRepository.save(user);
    }
}
