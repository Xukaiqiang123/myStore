package com.dao.impl;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.entity.Page;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    private static QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<Goods> selectByTypeId(int typeId) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods where typeid=?",new BeanListHandler<>(Goods.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long selectCountByTypeId(int typeId) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select count(*) from goods where typeid=?",new ScalarHandler<>(),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Goods> selectByTypeId(int typeId, Page page) {
        Object[] params = {typeId,page.getStartRows(),page.getPageSize()};
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods where typeid=? limit ?,?",new BeanListHandler<>(Goods.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods selectById(int id) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods where id=?",new BeanHandler<>(Goods.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> selectAll() {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods",new BeanListHandler<>(Goods.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goods> selectAll(String name, String pubDate) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods where name=? and pubDate=?",new BeanListHandler<>(Goods.class),name,pubDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long selectCount() {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select count(*) from goods",new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Goods goods) {
        Object[] params = {goods.getName(),goods.getPicture(),goods.getPubDate(),goods.getInfo(),goods.getStar(),goods.getPrice(),goods.getTypeId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into goods (name,picture,pubDate,info,star,price,typeId) values (?,?,?,?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"delete from goods where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int update(Goods goods) {
        Object[] params = {goods.getName(),goods.getPubDate(),goods.getInfo(),goods.getStar(),goods.getPrice(),goods.getId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update goods set name=?,pubDate=?,info=?,star=?,price=? where id=?",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
