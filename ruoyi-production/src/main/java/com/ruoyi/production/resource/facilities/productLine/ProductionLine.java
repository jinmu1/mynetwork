package com.ruoyi.production.resource.facilities.productLine;

import com.ruoyi.production.form.Goods;
import com.ruoyi.production.form.Production;
import com.ruoyi.production.queue.Point;

public class ProductionLine {
    private String code;//生产线编码
    private String station;//工位
    private Production production;//产品编码
    private Point point;
    private double times;//时间

    public ProductionLine() {
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getTimes() {
        return times;
    }

    public void setTimes(double times) {
        this.times = times;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    private Goods goods;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
