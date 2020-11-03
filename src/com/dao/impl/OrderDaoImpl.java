package com.dao.impl;

import com.dao.OrderDao;
import com.entity.*;
import com.utils.DruidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private static QueryRunner queryRunner = new QueryRunner();
    //添加订单
    @Override
    public int insertOrders(Orders orders) {
        Object[] params = {orders.getId(),orders.getuId(),orders.getMoney(),orders.getStatus(),orders.getTime(),orders.getaId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into orders values (?,?,?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //级联的生成订单详情
    @Override
    public void insertOrdersDetail(List<OrdersDetail> ordersDetails) {
        Object[][] params =new Object[ordersDetails.size()][];
        String sql = "insert into orders_detail (pid,oid,num,money) values(?,?,?,?)";
        for (int i = 0; i < ordersDetails.size(); i++) {
            OrdersDetail ordersDetail = ordersDetails.get(i);
            params[i] = new Object[]{ordersDetail.getpId(),ordersDetail.getoId(),ordersDetail.getNum(),ordersDetail.getMoney()};
        }
        try {
            queryRunner.batch(DruidUtils.getConnection(), sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders selectOrders(String oId) {
        Orders orders = new Orders();
        AddressInfo addressInfo = new AddressInfo();
        try {
            Map<String, Object> map = queryRunner.query(DruidUtils.getConnection(), "SELECT od.id,od.uid,od.money,od.status,od.time,od.aid,ad.detail,ad.name,ad.phone,ad.level,ad.uid FROM orders as od INNER JOIN address_info as ad ON od.aid = ad.id where od.id=?",
                    new MapHandler(), oId);
            BeanUtils.populate(orders,map);
            BeanUtils.populate(addressInfo,map);
            orders.setAddressInfo(addressInfo);
            //System.out.println("根据oid连表查询"+orders);
            //System.out.println("根据oid连表查询"+addressInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<OrdersDetail> selectDetails(String oId) {
        List<OrdersDetail> ordersDetails = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = queryRunner.query(DruidUtils.getConnection(), "SELECT od.id,od.pid,od.oid,od.num,od.money,ad.id,ad.name,ad.pubDate,ad.picture,ad.price,ad.star,ad.info,ad.typeid FROM orders_detail\n" +
                            "as od INNER JOIN goods as ad ON od.pid = ad.id where od.oid=?",
                    new MapListHandler(), oId);
            for (int i = 0; i < maps.size(); i++) {
                OrdersDetail ordersDetail = new OrdersDetail();
                Goods goods = new Goods();
                BeanUtils.populate(ordersDetail, maps.get(i));
                BeanUtils.populate(goods, maps.get(i));
                //System.out.println("根据oid关联详情表和商品表"+goods);
                ordersDetail.setGoods(goods);
                ordersDetails.add(ordersDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ordersDetails;
    }

    @Override
    public List<Orders> searchOrders(int uId, int status) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from orders where uid=? and status=?",new BeanListHandler<>(Orders.class),uId,status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(String oId) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update orders set status=2 where id=?",oId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int updateSend(String oId) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update orders set status=3 where id=?",oId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int getGoods(String oId) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update orders set status=4 where id=?",oId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<Orders> selectAllOrders(int uId) {
        List<Orders> ordersList = null;
        try {
            List<Map<String, Object>> maps = queryRunner.query(DruidUtils.getConnection(),
                    "select od.id, od.uid, od.money, od.status, od.time, od.aid,\n" +
                    "ad.detail, ad.name, ad.phone,ad.level,ad.uid from orders as od\n" +
                    "inner join address_info as ad on od.aid = ad.id where od.uid = ?",
                    new MapListHandler(), uId);
            if(maps == null){
                return null;
            }
            ordersList = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                Orders orders = new Orders();
                AddressInfo address = new AddressInfo();
                BeanUtils.populate(orders, map);
                BeanUtils.populate(address, map);
                orders.setAddressInfo(address);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ordersList;
    }
    @Override
    public List<Orders> selectAllOrders(){
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from orders",new BeanListHandler<>(Orders.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User selectUser(int uId) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from user where id=?",new BeanHandler<>(User.class),uId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
