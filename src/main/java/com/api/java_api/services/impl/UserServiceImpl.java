package com.api.java_api.services.impl;

import com.api.java_api.entities.User;
import com.api.java_api.repositories.UserRepository;
import com.api.java_api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

    @Override
    public User getName(User user) {
        return userRepository.save(user);
    }
}
