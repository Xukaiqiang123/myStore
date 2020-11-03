package com.dao.impl;

import com.dao.CartDao;
import com.entity.Cart;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private static QueryRunner queryRunner = new QueryRunner();
    @Override
    public Cart selectOne(int gid, int id) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from cart where id=? and gid=?",new BeanHandler<>(Cart.class),id,gid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Cart cart) {
        Object[] params = {cart.getId(),cart.getgId(),cart.getNum(),cart.getMoney()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into cart values (?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int update(Cart cart) {
        Object[] params = {cart.getNum(),cart.getMoney(),cart.getId(),cart.getgId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update cart set num=?,money=? where id = ? and gid=?",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cart> selectAllById(int id) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from cart where id=?",new BeanListHandler<>(Cart.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(int gId,int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"delete from cart where gid = ? and id = ?",gId,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int clear(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"delete from cart where id = ?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
