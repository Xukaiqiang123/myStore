package com.entity;

public class OrdersDetail {
    private int id;
    private int pId;
    private String oId;
    private int num;
    private double money;
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrdersDetail() {}

    public OrdersDetail(int pId, String oId, int num, double money) {
        this.pId = pId;
        this.oId = oId;
        this.num = num;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "id=" + id +
                ", pId=" + pId +
                ", oId='" + oId + '\'' +
                ", num=" + num +
                ", money=" + money +
                ", goods=" + goods +
                '}';
    }
}
