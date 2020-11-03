package com.service;

import com.entity.AddressInfo;

import javax.mail.Address;
import java.util.List;

public interface AddressService {
    List<AddressInfo> getAddressByUid(int uId);
    int addAddress(AddressInfo addressInfo);
    int deleteAddress(int id);
    int updateAddress(int id,int uId);
    int updateAddress(AddressInfo addressInfo);
}
