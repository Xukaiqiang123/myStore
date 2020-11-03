package com.dao;

import com.entity.GoodsType;

import java.util.List;

public interface GoodsTypeDao {
    List<GoodsType> selectAll();
    int update(GoodsType goodsType);
    int delete(int id);
    List<GoodsType> search(String name,int level);
    List<GoodsType> searchByLevel(int level);
    List<GoodsType> searchByName(String name);
    int insert(GoodsType goodsType);
    GoodsType select(int id);
}
