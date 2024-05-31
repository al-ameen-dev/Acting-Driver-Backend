package com.alameendev.ActingDriver.user.service;

import com.alameendev.ActingDriver.user.entity.User;

public interface UserService {
    User getUserByEmail(String email);

    User getUserById(Long id);
    User saveUser(User user);
}
