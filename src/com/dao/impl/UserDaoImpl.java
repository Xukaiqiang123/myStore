package com.dao.impl;

import com.dao.UserDao;
import com.entity.User;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static QueryRunner queryRunner = new QueryRunner();
    @Override
    public User select(String username) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where username=?",new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(User user) {
        Object[] params = {user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getFlag(),user.getRole(),user.getCode()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into user(username,password,email,gender,flag,role,code) values (?,?,?,?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateFlag(String email, String code) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update user set flag = 1 where email=? and code=?",email,code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> selectUser() {
        List<User> userList = null;
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where flag = 1",new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<User> selectUser(String username,String gender) {
        List<User> userList = null;
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where flag = 1 and username=? and gender=?",new BeanListHandler<>(User.class),username,gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<User> selectUsers(String gender) {
        List<User> userList = null;
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where flag = 1 and gender=?",new BeanListHandler<>(User.class),gender);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<User> selectUser(String username) {
        List<User> userList = null;
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where flag = 1 and username=?",new BeanListHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int delete(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update user set flag = 2 where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
