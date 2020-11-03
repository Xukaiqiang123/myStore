package com.dao;

import com.entity.Goods;
import com.entity.Page;

import java.util.List;

public interface GoodsDao {
    List<Goods> selectByTypeId(int typeId);
    long selectCountByTypeId(int typeId);
    List<Goods> selectByTypeId(int typeId, Page page);
    Goods selectById(int id);
    List<Goods> selectAll();
    List<Goods> selectAll(String name,String pubDate);
    long selectCount();
    int insert(Goods goods);
    int delete(int id);
    int update(Goods goods);
}
