package com.ruoyi.warehousing.resource;

import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.result.Point;

import java.util.Objects;

public class Cargo {
    private Point point;
    private Goods goods;
    public Cargo() {

    }

    @Override
    public String toString() {
        return "物料:" + goods.getGoodsCode()+"---坐标:" + point;
    }

    public Cargo(Point point, Goods goods) {
        this.point = point;
        this.goods = goods;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
