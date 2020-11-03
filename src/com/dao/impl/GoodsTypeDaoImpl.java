package com.dao.impl;

import com.dao.GoodsTypeDao;
import com.entity.GoodsType;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeDaoImpl implements GoodsTypeDao {
    private static QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<GoodsType> selectAll() {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods_type where level = 1",new BeanListHandler<>(GoodsType.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(GoodsType goodsType) {
        Object[] params = {goodsType.getLevel(),goodsType.getName(),goodsType.getParent(),goodsType.getId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update goods_type set level=?,name=?,parent=? where id=?",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"delete from goods_type where id =?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<GoodsType> search(String name, int level) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods_type where name=? and level=?",new BeanListHandler<>(GoodsType.class),name,level);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsType> searchByLevel(int level) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods_type where level=?",new BeanListHandler<>(GoodsType.class),level);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsType> searchByName(String name) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods_type where name=?",new BeanListHandler<>(GoodsType.class),name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(GoodsType goodsType) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into goods_type (name,level,parent) values (?,?,?)",goodsType.getName(),goodsType.getLevel(),goodsType.getParent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public GoodsType select(int id) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from goods_type where id =?",new BeanHandler<>(GoodsType.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
