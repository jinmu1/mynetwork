package com.ruoyi.production.network;

import com.ruoyi.production.form.City;
import com.ruoyi.production.form.Goods;
import com.ruoyi.production.resource.facilities.storage.Storage;

import java.util.List;

/**
 * 中间仓
 */
public class Midbin {
    private String code;//中间仓
    private double area;//面积
    private List<Goods> goodsList;//中间仓物料编码
    private int emp;//中间仓人员数量
    private int platform;//月台数量
    private Storage storage;
    private City city;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
