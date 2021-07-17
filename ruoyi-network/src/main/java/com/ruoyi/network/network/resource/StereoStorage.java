package com.ruoyi.network.network.resource;

/**
 * 自动化立库内容
 */
public class StereoStorage {

    private int  row;//排
    private int layer;//层
    private int  line;//列
    private  double area;//面积
    private double price;//设备总价
    private int  cargo;//货位数量
    private int stacker;//堆垛机数量
    private double belt;//传送带长度

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
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

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getStacker() {
        return stacker;
    }

    public void setStacker(int stacker) {
        this.stacker = stacker;
    }

    public double getBelt() {
        return belt;
    }

    public void setBelt(double belt) {
        this.belt = belt;
    }
}
