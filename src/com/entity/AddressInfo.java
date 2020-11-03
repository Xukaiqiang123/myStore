package com.entity;

public class AddressInfo {
    private int id;
    private String detail;
    private String name;
    private String phone;
    private int level;
    private int uid;
    private User user;

    public AddressInfo() {}

    public AddressInfo(String detail, String name, String phone, int level, int uid) {
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.level = level;
        this.uid = uid;
    }

    public AddressInfo(int id, String detail, String name, String phone, int level, int uid) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.level = level;
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                ", uid=" + uid +
                '}';
    }
}
