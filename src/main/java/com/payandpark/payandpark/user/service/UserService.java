package com.payandpark.payandpark.user.service;

import com.payandpark.payandpark.user.model.CreateUserRequest;
import com.payandpark.payandpark.user.model.User;

public interface UserService {
    void createUser(CreateUserRequest mobile);
    User fetchUserById(int userId);
}
