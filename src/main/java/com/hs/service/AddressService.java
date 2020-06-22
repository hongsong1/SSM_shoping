package com.hs.service;

import com.hs.entity.Address;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-31 10:56
 **/
public interface AddressService {
    public List<Address> getAll(Integer cid);

    public int update(Address addresss);


}
