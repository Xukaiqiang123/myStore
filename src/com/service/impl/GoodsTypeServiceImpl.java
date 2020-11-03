package com.service.impl;

import com.dao.GoodsTypeDao;
import com.dao.impl.GoodsTypeDaoImpl;
import com.entity.GoodsType;
import com.service.GoodsTypeService;
import com.utils.DruidUtils;

import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {
    private static GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> getAllGoodsType() {
        List<GoodsType> list = null;
        try {
            DruidUtils.begin();
            list = goodsTypeDao.selectAll();
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteType(int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            goodsTypeDao.delete(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public int updateType(GoodsType goodsType) {
        int sign = 0;
        try {
            DruidUtils.begin();
            goodsTypeDao.update(goodsType);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public List<GoodsType> searchType(String name, String level) {
        List<GoodsType> list = null;
        try {
            DruidUtils.begin();
            if (!name.equals("") && !level.equals("")) {
                list = goodsTypeDao.search(name,Integer.valueOf(level));
            }else if (name.equals("") && !level.equals("")) {
                list = goodsTypeDao.searchByLevel(Integer.valueOf(level));
            }else if (!name.equals("") && level.equals("")) {
                list = goodsTypeDao.searchByName(name);
            }else{
                list = goodsTypeDao.selectAll();
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addGoodsType(GoodsType goodsType) {
        int sign = 0;
        try {
            DruidUtils.begin();
            goodsTypeDao.insert(goodsType);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
}
