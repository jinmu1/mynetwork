package com.ruoyi.system.domain.network;

public class Material {
       private String name;//物料名称
       private String code;//物料编码
       private double price;//物料价值
       private double volume;//物料体积
       private double needNum;//物料需求量
       private double frequency;//物料需求频次


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getNeedNum() {
        return needNum;
    }

    public void setNeedNum(double needNum) {
        this.needNum = needNum;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
