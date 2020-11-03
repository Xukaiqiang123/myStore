package com.service;

import com.entity.Cart;

import java.util.List;

public interface CartService {
    int insertCart(int gId,int id,int price);
    List<Cart> getCartsById(int id);
    int deleteCart(int gId,int id);
    int clearAllCart(int id);
    int updateCart(Cart cart);
}
