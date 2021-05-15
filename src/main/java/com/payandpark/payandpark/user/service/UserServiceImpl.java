package com.payandpark.payandpark.user.service;

import com.payandpark.payandpark.user.model.CreateUserRequest;
import com.payandpark.payandpark.user.model.User;
import com.payandpark.payandpark.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public void createUser(CreateUserRequest request) {
        log.info("Creating user for mobile :: {}", request.getMobile());
        repository.createUser(request);
    }

    @Override
    public User fetchUserById(int userId) {
        log.info("Fetching user by id :: {}", userId);
        return repository.fetchUserById(userId);
    }
}
