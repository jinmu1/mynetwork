package com.ruoyi.system.network.network;

import java.util.List;

public class Message {
    private double cost;
    private List<Result> resultList;
    private List<City> cityList;

    public double getCost() {
        return cost;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
