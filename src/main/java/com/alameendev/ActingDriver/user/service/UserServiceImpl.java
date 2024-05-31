package com.alameendev.ActingDriver.user.service;

import com.alameendev.ActingDriver.exceptions.user.UserNotFoundException;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
