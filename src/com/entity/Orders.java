package com.entity;

import java.util.Date;
import java.util.List;

public class Orders {
    private String id;
    private int uId;
    private double money;
    private int status;
    private int aId;
    private Date time;
    private User user;
    private List<OrdersDetail> list;
    private AddressInfo addressInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<OrdersDetail> getList() {
        return list;
    }

    public void setList(List<OrdersDetail> list) {
        this.list = list;
    }

    public Orders() {}

    public Orders(String id, int uId, double money, int status, int aId, Date time) {
        this.id = id;
        this.uId = uId;
        this.money = money;
        this.status = status;
        this.aId = aId;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", uId=" + uId +
                ", money=" + money +
                ", status=" + status +
                ", aId=" + aId +
                ", time=" + time +
                ", ordersDetails=" + list +
                ", addressInfo=" + addressInfo +
                '}';
    }
}
