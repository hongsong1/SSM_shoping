package com.hs.service.impl;

import com.hs.dao.AddressDao;
import com.hs.entity.Address;
import com.hs.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-31 10:57
 **/
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    public List<Address> getAll(Integer cid) {
        return addressDao.getAll(cid);
    }

    public int update(Address address) {
        if(address.getId()!=null){
            return addressDao.update(address);
        }else{
            return addressDao.add(address);
        }

    }

}
