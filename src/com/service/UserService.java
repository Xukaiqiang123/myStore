package com.service;

import com.entity.User;

import java.util.List;

public interface UserService {
    User checkName(String username);
    int register(User user);
    User login(String username,String password);
    int activeUser(String email,String code);
    List<User> getUsers();
    List<User> getUsers(String username,String gender);
    int deleteUser(int id);
}
