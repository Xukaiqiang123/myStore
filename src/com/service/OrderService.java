package com.service;

import com.entity.Orders;

import java.util.List;

public interface OrderService {
    int addOrdersOne(Orders orders);
    List<Orders> lookAllOrders(int uId);
    List<Orders> getAllOrders();
    Orders lookOrdersDetail(String oId);
    int updateStatus(String oId);
    int sendOrders(String oId);
    int changeStatus(String oId);
    List<Orders> searchOrder(String username,String status);
}
