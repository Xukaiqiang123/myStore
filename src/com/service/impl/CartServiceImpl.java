package com.service.impl;

import com.dao.CartDao;
import com.dao.GoodsDao;
import com.dao.impl.CartDaoImpl;
import com.dao.impl.GoodsDaoImpl;
import com.entity.Cart;
import com.entity.Goods;
import com.service.CartService;
import com.service.GoodsService;
import com.utils.DruidUtils;
import java.util.List;

public class CartServiceImpl implements CartService {
    private static CartDao cartDao = new CartDaoImpl();
    private static GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public int insertCart(int gId, int id, int price) {
        int sign = 0;
        try {
            DruidUtils.begin();
            Cart cart = cartDao.selectOne(gId, id);
            if (cart == null) {
                sign = cartDao.insert(new Cart(id,gId,1,price));
            }else{
                sign = cartDao.update(new Cart(id,gId,cart.getNum()+1,cart.getMoney()+price));
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public List<Cart> getCartsById(int id) {
        List<Cart> carts = null;
        try {
            DruidUtils.begin();
            carts = cartDao.selectAllById(id);
            for (Cart cart:carts) {
                Goods goods = goodsDao.selectById(cart.getgId());
                cart.setGoods(goods);
            }
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public int deleteCart(int gId, int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = cartDao.delete(gId, id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
    @Override
    public int clearAllCart(int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = cartDao.clear(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public int updateCart(Cart cart) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = cartDao.update(cart);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
}
