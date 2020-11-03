package com.service.impl;

import com.dao.GoodsDao;
import com.dao.GoodsTypeDao;
import com.dao.impl.GoodsDaoImpl;
import com.dao.impl.GoodsTypeDaoImpl;
import com.entity.Cart;
import com.entity.Goods;
import com.entity.GoodsType;
import com.entity.Page;
import com.service.GoodsService;
import com.service.GoodsTypeService;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.lang.model.element.VariableElement;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private static GoodsDao goodsDao = new GoodsDaoImpl();
    private static GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();

    @Override
    public List<Goods> selectGoodsByTypeId(int typeId) {
        List<Goods> goodsList = null;
        try {
            DruidUtils.begin();
            goodsList = goodsDao.selectByTypeId(typeId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return goodsList;
    }
    @Override
    public List<Goods> getByTypeId(int typeId, Page page) {
        List<Goods> goodsList = null;
        try {
            DruidUtils.begin();
            goodsList = goodsDao.selectByTypeId(typeId,page);
            page.setCountRows((int)goodsDao.selectCountByTypeId(typeId));
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public Goods getGoodsById(int id) {
        Goods goods = null;
        try {
            DruidUtils.begin();
            goods = goodsDao.selectById(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goodsList = null;
        try {
            DruidUtils.begin();
            goodsList = goodsDao.selectAll();
            for (int i = 0; i < goodsList.size(); i++) {
                GoodsType goodsType = goodsTypeDao.select(goodsList.get(i).getTypeId());
                goodsList.get(i).setGoodsType(goodsType);
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public List<Goods> searchGoods(String name, String pubDate) {
        List<Goods> goodsList = null;
        try {
            DruidUtils.begin();
            if (name.equals("")&&pubDate.equals("")){
                goodsList = goodsDao.selectAll();
            }else {
                goodsList = goodsDao.selectAll(name, pubDate);
            }
            for (int i = 0; i < goodsList.size(); i++) {
                GoodsType goodsType = goodsTypeDao.select(goodsList.get(i).getTypeId());
                goodsList.get(i).setGoodsType(goodsType);
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public int addGoods(Goods goods) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = goodsDao.insert(goods);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
    @Override
    public int deleteGoods(int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = goodsDao.delete(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
    @Override
    public int updateGoods(Goods goods) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = goodsDao.update(goods);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
}
