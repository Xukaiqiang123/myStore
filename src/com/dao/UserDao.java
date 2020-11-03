package com.dao;

import com.entity.User;

import java.util.List;

public interface UserDao {
    User select(String username);
    int insert(User user);
    int updateFlag(String email,String code);
    List<User> selectUser();
    List<User> selectUser(String username);
    List<User> selectUsers(String gender);
    List<User> selectUser(String username,String gender);
    int delete(int id);
}
