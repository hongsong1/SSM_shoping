package com.hs.dao;

import com.hs.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-31 10:38
 **/
@Repository
public interface AddressDao {

    public List<Address> getAll(Integer cid);

    public int update(Address addresss);

    public int add(Address address);
}
