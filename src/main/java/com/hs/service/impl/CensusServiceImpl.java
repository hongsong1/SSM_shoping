package com.hs.service.impl;

import com.hs.dao.CensusDao;
import com.hs.service.CenesusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-05-11 21:33
 **/
@Service
public class CensusServiceImpl implements CenesusService {
    @Autowired
    private CensusDao censusDao;
    @Override
    public Map<String, Object> getOneNewOrder() {
        List<Map<String, Object>> newOrder = censusDao.getOneNewOrder();
        Map<String,Object>maplist=new HashMap<>();
        if(newOrder!=null&&newOrder.size()>0){
            List xlist=new ArrayList();
            List datalist=new ArrayList();
            for (Map<String, Object> map : newOrder) {
                xlist.add(map.get("ym"));
                datalist.add(map.get("ctotal"));
            }
            maplist.put("year",xlist);
            maplist.put("data",datalist);
        }

        return maplist;
    }

    @Override
    public List<Map<String,Object>>getOneNewType() {
        return censusDao.getOneNewType();
    }
}
