package com.payandpark.payandpark.user.service;

import com.payandpark.payandpark.user.model.CreateUserRequest;

public interface UserService {
    void createUser(CreateUserRequest mobile);
}
