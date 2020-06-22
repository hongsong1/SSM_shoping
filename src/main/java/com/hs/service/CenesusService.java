package com.hs.service;

import java.util.List;
import java.util.Map;

/**
 * @author: 彭于晏
 * @create: 2020-05-11 21:32
 **/
public interface CenesusService {
    Map<String,Object>getOneNewOrder();

    List<Map<String,Object>> getOneNewType();
}
