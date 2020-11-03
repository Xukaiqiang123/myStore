package com.service.impl;

import com.dao.AddressDao;
import com.dao.impl.AddressDaoImpl;
import com.entity.AddressInfo;
import com.service.AddressService;
import com.utils.DruidUtils;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    @Override
    public List<AddressInfo> getAddressByUid(int uId) {
        List<AddressInfo> list = null;
        try {
            DruidUtils.begin();
            list = addressDao.selectByUid(uId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int addAddress(AddressInfo addressInfo) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = addressDao.insert(addressInfo);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public int deleteAddress(int id) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = addressDao.delete(id);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }

    @Override
    public int updateAddress(int id, int uId) {
        int setDefault = 0,signGeneral = 0;
        try {
            DruidUtils.begin();
            setDefault = addressDao.update(id);
            signGeneral = addressDao.update(id,uId);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        if (setDefault == signGeneral&&setDefault!=0) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int updateAddress(AddressInfo addressInfo) {
        int sign = 0;
        try {
            DruidUtils.begin();
            sign = addressDao.update(addressInfo);
            DruidUtils.commit();
        } catch (Exception e) {
            DruidUtils.rollback();
            e.printStackTrace();
        }
        return sign;
    }
}
