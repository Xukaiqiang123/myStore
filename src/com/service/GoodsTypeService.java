package com.service;

import com.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> getAllGoodsType();
    int deleteType(int id);
    int updateType(GoodsType goodsType);
    List<GoodsType> searchType(String name,String level);
    int addGoodsType(GoodsType goodsType);
}
