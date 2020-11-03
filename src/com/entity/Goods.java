package com.entity;

import java.util.Date;

public class Goods {
    private int id;
    private String name;
    private String picture;
    private Date pubDate;
    private int price;
    private String info;
    private int star;
    private int typeId;
    private GoodsType goodsType;

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public Goods() {}

    public Goods(int id, String name, Date pubDate,String picture, int price, int star, String info,int typeId) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.pubDate = pubDate;
        this.price = price;
        this.info = info;
        this.star = star;
        this.typeId = typeId;
    }

    public Goods(String name, String picture, Date pubDate, int price, String info, int star, int typeId) {
        this.name = name;
        this.picture = picture;
        this.pubDate = pubDate;
        this.price = price;
        this.info = info;
        this.star = star;
        this.typeId = typeId;
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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", pubDate=" + pubDate +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", star=" + star +
                ", typeId=" + typeId +
                '}';
    }
}
