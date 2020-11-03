package com.dao;

import com.entity.AddressInfo;

import java.util.List;

public interface AddressDao {
    List<AddressInfo> selectByUid(int uId);
    int insert(AddressInfo addressInfo);
    int delete(int id);
    int update(int id, int uId);
    int update(int id);
    int update(AddressInfo addressInfo);
}
