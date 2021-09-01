package com.ruoyi.production.network;

import com.ruoyi.production.form.City;

/**
 * 线边仓
 */
public class TemporaryStorage {
    private City city;//
    private double area;//面积
    private double toMidbinDistance;//中间仓距离
    private double toFactoryDistance;//到工厂距离
    


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getToMidbinDistance() {
        return toMidbinDistance;
    }

    public void setToMidbinDistance(double toMidbinDistance) {
        this.toMidbinDistance = toMidbinDistance;
    }

    public double getToFactoryDistance() {
        return toFactoryDistance;
    }

    public void setToFactoryDistance(double toFactoryDistance) {
        this.toFactoryDistance = toFactoryDistance;
    }
}
