package com.dao;

import com.entity.Orders;
import com.entity.OrdersDetail;
import com.entity.User;

import java.util.List;

public interface OrderDao {
    int insertOrders(Orders orders);
    List<Orders> selectAllOrders(int uId);
    void insertOrdersDetail(List<OrdersDetail> ordersDetails);
    Orders selectOrders(String oId);
    List<OrdersDetail> selectDetails(String oId);
    List<Orders> searchOrders(int uId,int status);
    int update(String oId);
    int updateSend(String oId);
    int getGoods(String oId);
    List<Orders> selectAllOrders();
    User selectUser(int uId);
}
