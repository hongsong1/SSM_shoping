package com.hs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-17 23:38
 **/
@Data
public class QueryBody {
    private Integer pageNumber;
    private Integer pageSize;


       private String searchStartTime;
    private String searchEndTime;
    private String searchName;
    private Integer searchStatus;

    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    @Override
    public String toString() {
        return "QueryBody{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", searchStartTime=" + searchStartTime +
                ", searchEndTime=" + searchEndTime +
                ", searchName='" + searchName + '\'' +
                ", searchStatus=" + searchStatus +
                '}';
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

//    public Date getSearchStartTime() {
//        return searchStartTime;
//    }
//
//    public void setSearchStartTime(Date searchStartTime) {
//        this.searchStartTime = searchStartTime;
//    }
//
//    public Date getSearchEndTime() {
//        return searchEndTime;
//    }
//
//    public void setSearchEndTime(Date searchEndTime) {
//        this.searchEndTime = searchEndTime;
//    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(Integer searchStatus) {
        this.searchStatus = searchStatus;
    }
}
