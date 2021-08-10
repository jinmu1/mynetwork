package com.ruoyi.warehousing.resource.equipment;

import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;

import java.util.List;

/**
 * 托盘
 */
public class Tray {
    private static double width=1.1;//托盘的宽
    private static double length=1.2;//托盘的长
    private static double thickness=0.6;//托盘的厚度
    private static double height=1.6;//托盘的高度---限高
    private List<Goods> goodsList;//存储的物料数量
    private Point point;
    private int status;//托盘的状态 0-空 1-有物料 2-满载了

    public Tray(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
    public Tray() {
        super();
    }
    public Tray(double width, double length, double thickness, double height, int status) {
        this.width = width;
        this.length = length;
        this.thickness = thickness;
        this.height = height;
        this.goodsList = goodsList;
        this.point = point;
        this.status = status;
    }

    private void init(){

    }

    public double getWidth() {
        return width;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
