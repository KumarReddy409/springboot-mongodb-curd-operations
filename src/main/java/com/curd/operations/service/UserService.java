package com.curd.operations.service;

import com.curd.operations.entity.User;
import com.curd.operations.exception.CurdOperationsException;

import java.util.List;

public interface UserService {
    String addUser(User user) throws CurdOperationsException;

    List<User> getAllUsers();

    String deleteUserByEmail(String email) throws CurdOperationsException;

    String updateUser(User user) throws CurdOperationsException;
}
