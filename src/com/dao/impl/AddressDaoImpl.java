package com.dao.impl;

import com.dao.AddressDao;
import com.entity.AddressInfo;
import com.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private static QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<AddressInfo> selectByUid(int uId) {
        try {
            return queryRunner.query(DruidUtils.getConnection(),"select * from address_info where uid=? ORDER BY level desc",new BeanListHandler<>(AddressInfo.class),uId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(AddressInfo addressInfo) {
        Object[] params = {addressInfo.getName(),addressInfo.getDetail(),addressInfo.getPhone(),addressInfo.getLevel(),addressInfo.getUid()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"insert into address_info (name,detail,phone,level,uid) values (?,?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"delete from address_info where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(int id, int uId) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update address_info set level = 0 where id !=? and uid=?",id,uId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(int id) {
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update address_info set level = 1 where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(AddressInfo addressInfo) {
        Object[] params = {addressInfo.getName(),addressInfo.getDetail(),addressInfo.getPhone(),addressInfo.getLevel(),addressInfo.getUid(),addressInfo.getId()};
        try {
            return queryRunner.update(DruidUtils.getConnection(),"update address_info set name=?,detail=?,phone=?,level=?,uid=? where id=?",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
