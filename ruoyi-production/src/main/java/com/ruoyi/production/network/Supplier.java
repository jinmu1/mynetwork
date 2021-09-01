package com.ruoyi.production.network;

import com.ruoyi.production.form.City;
import com.ruoyi.production.form.Goods;

import java.util.List;

public class Supplier {
    private String SupplierCode;
    private List<Goods> goodsList;//物料表
    private String distance;//运输距离
    private double leadTime;//运输提前期
    private String type;//配送类型
    private City city;
    private double free;//单次运输费率


    public double getFree() {
        return free;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Supplier() {
        super();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getSupplierCode() {
        return SupplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        SupplierCode = supplierCode;
    }



    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(double leadTime) {
        this.leadTime = leadTime;
    }
}
