package com.service.impl;

import com.dao.CartDao;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.dao.impl.CartDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.entity.Cart;
import com.entity.Orders;
import com.entity.OrdersDetail;
import com.entity.User;
import com.service.OrderService;
import com.utils.DruidUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderDao orderDao = new OrderDaoImpl();
    private static CartDao cartDao = new CartDaoImpl();
    private static UserDao userDao = new UserDaoImpl();
    @Override
    public int addOrdersOne(Orders orders) {
        try {
            DruidUtils.begin();
            orderDao.insertOrders(orders);
            List<OrdersDetail> ordersDetails = new ArrayList<>();
            //3.获取购物车集合。  购物车中每条数据都对应着订单详情的一条数据
            List<Cart> carts = cartDao.selectAllById(orders.getuId());
            for (Cart cart : carts) {
                //每循环一个购物车对象，就要生成一条订单详情
                OrdersDetail ordersDetail = new OrdersDetail(cart.getgId(),orders.getId(),cart.getNum(),cart.getMoney());
                ordersDetails.add(ordersDetail);
            }
            //4.级联添加订单详情。
            orderDao.insertOrdersDetail(ordersDetails);
            //5.清空购物车。
            cartDao.clear(orders.getuId());
            DruidUtils.commit();
            return 1;
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Orders> lookAllOrders(int uId) {
        List<Orders> orders =null;
        //1.
        try {
            DruidUtils.begin();

            orders = orderDao.selectAllOrders(uId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Orders lookOrdersDetail(String oId) {
        Orders orders = null;
        try {
            DruidUtils.begin();
            //连表查询订单信息和地址信息
            orders = orderDao.selectOrders(oId);
            //连表查询所有订单详情和订单商品信息
            List<OrdersDetail> ordersDetails = orderDao.selectDetails(oId);
            //添加到新建订单类的对象返回
            orders.setList(ordersDetails);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int updateStatus(String oId) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = orderDao.update(oId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public int sendOrders(String oId) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = orderDao.updateSend(oId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
    @Override
    public int changeStatus(String oId) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = orderDao.getGoods(oId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public List<Orders> searchOrder(String username, String status) {
        List<Orders> orders =null;
        //1.
        try {
            DruidUtils.begin();
            User user = userDao.select(username);
            orders = orderDao.searchOrders(user.getId(),Integer.valueOf(status));
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Orders> getAllOrders(){
        List<Orders> orders =null;
        //1.
        try {
            DruidUtils.begin();
            orders = orderDao.selectAllOrders();
            for (int i = 0; i < orders.size(); i++) {
                User user = new User();
                user = orderDao.selectUser(orders.get(i).getuId());
                orders.get(i).setUser(user);
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return orders;
    }
}
