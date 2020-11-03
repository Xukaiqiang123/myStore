package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.service.UserService;
import com.utils.DruidUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao = new UserDaoImpl();
    @Override
    public User checkName(String username) {
        User user = null;
        try {
            DruidUtils.begin();
            user = userDao.select(username);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int register(User user) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = userDao.insert(user);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        try {
            DruidUtils.begin();
            user = userDao.select(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    DruidUtils.commit();
                    return user;
                }
            }
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int activeUser(String email, String code) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = userDao.updateFlag(email,code);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = null;
        try {
            DruidUtils.begin();
            userList = userDao.selectUser();
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public List<User> getUsers(String username,String gender) {
        List<User> userList = null;
        try {
            DruidUtils.begin();
            if (!username.equals("") && !gender.equals("")) {
                userList = userDao.selectUser(username,gender);
            }else if (username.equals("") && !gender.equals("")) {
                userList = userDao.selectUsers(gender);
            }else if (!username.equals("") && gender.equals("")) {
                userList = userDao.selectUser(username);
            }else{
                userList = userDao.selectUser();
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public int deleteUser(int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = userDao.delete(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
}
