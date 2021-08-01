package com.ruoyi.warehousing.form;

import com.ruoyi.warehousing.queue.Point;

import java.util.Objects;

public class Goods {
    private String type;//车间类型
    private String goodsCode;//物料名称
    private double plutNum;//托盘数
    private String productLine;//产线
    private int status;
    private Point position;//位置
    private String area;//区域

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Goods() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;
        Goods goods = (Goods) o;
        return Double.compare(goods.getPlutNum(), getPlutNum()) == 0 &&
                getType().equals(goods.getType()) &&
                getGoodsCode().equals(goods.getGoodsCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getGoodsCode(), getPlutNum());
    }

    public Goods(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Goods(String goodsCode, double plutNum) {
        this.goodsCode = goodsCode;
        this.plutNum = plutNum;
    }

    public Goods(String goodsCode, double plutNum, String type) {
        this.goodsCode = goodsCode;
        this.plutNum = plutNum;
        this.type  =type;
    }
    public Goods(String goodsCode, double plutNum,String type,String productLine) {
        this.goodsCode = goodsCode;
        this.plutNum = plutNum;
        this.type  =type;
        this.productLine = productLine;
    }
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public double getPlutNum() {
        return plutNum;
    }

    public void setPlutNum(double plutNum) {
        this.plutNum = plutNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
