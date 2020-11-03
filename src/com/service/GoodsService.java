package com.service;

import com.entity.Goods;
import com.entity.Page;

import java.util.List;

public interface GoodsService {
    List<Goods> selectGoodsByTypeId(int typeId);
    //int getCountByTypeId(int typeId);
    List<Goods> getByTypeId(int typeId, Page page);
    Goods getGoodsById(int id);
    List<Goods> getAllGoods();
    List<Goods> searchGoods(String name, String pubDate);
    int addGoods(Goods goods);
    int deleteGoods(int id);
    int updateGoods(Goods goods);
}
