package com.ruoyi.network.resource;

import com.ruoyi.network.enumType.StorageType;

import static com.ruoyi.network.utils.AreaUtils.getTime;

public  class Storage {
     private String type;
     private double area;
     private double price;//价格

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
