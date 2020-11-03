package com.entity;

import java.util.Date;

public class GoodsDetail {
    private int id;
    private String name;
    private String picture;
    private Date pubDate;
    private int price;
    private String info;
    private int star;
    private int typeId;
    private GoodsType goodsType;

    public GoodsDetail(int id, String name, String picture, Date pubDate, int price, String info, int star, int typeId, GoodsType goodsType) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.pubDate = pubDate;
        this.price = price;
        this.info = info;
        this.star = star;
        this.typeId = typeId;
        this.goodsType = goodsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }
}
