package com.dao;

import com.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart selectOne(int gid,int id);
    int insert(Cart cart);
    int update(Cart cart);
    List<Cart> selectAllById(int id);
    int delete(int gId,int id);
    int clear(int id);
}
