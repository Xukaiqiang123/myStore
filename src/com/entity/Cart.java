package com.entity;

public class Cart {
    private int id;
    private int gId;
    private int num;
    private double money;
    private Goods goods;

    public Cart() {}

    public Cart(int id, int gId, int num, double money) {
        this.id = id;
        this.gId = gId;
        this.num = num;
        this.money = money;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
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
        return "ShoppingCart{" +
                "id=" + id +
                ", gId=" + gId +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
