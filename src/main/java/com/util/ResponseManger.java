package com.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 响应数据的封装
 * @author: 彭于晏
 * @create: 2020-03-09 22:35
 **/
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Data
public class ResponseManger {

    private Integer successCode;
    private Integer erroCode;
    private Object object;

private Map<String,Object> mapObject = new HashMap<String, Object>();

    public Map<String, Object> getMapObject() {
        return mapObject;
    }

    public void setMapObject(Map<String, Object> mapObject) {
        this.mapObject = mapObject;
    }

    public Integer getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(Integer successCode) {
        this.successCode = successCode;
    }

    public Integer getErroCode() {
        return erroCode;
    }

    public void setErroCode(Integer erroCode) {
        this.erroCode = erroCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ResponseManger addObject(String name,Object object){
        this.mapObject.put(name,object);
       return this;
    }

    public static ResponseManger success(){
        ResponseManger reponseManger = new ResponseManger();
        reponseManger.successCode=100;
        return reponseManger;
    }
    public static ResponseManger erro(){
        ResponseManger reponseManger = new ResponseManger();
        reponseManger.erroCode=101;
        return reponseManger;
    }
    public static ResponseManger errorRole(){
        ResponseManger reponseManger = new ResponseManger();
        reponseManger.successCode=0;
        reponseManger.erroCode=500;
        return reponseManger;
    }
    public static ResponseManger error(String message){
        ResponseManger reponseManger = new ResponseManger();
        reponseManger.erroCode=501;
        reponseManger.setObject(message);
        return reponseManger;
    }
}
