package com.ruoyi.system.network.resource;

public class LightStorage {
    private double area;//面积
    private int  line;//排
    private int  row;//列
    private int layer;//层
    private int cargo;//货位数
    private double price;//价格
    private double emp;//作业人数

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

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

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEmp() {
        return emp;
    }

    public void setEmp(double emp) {
        this.emp = emp;
    }
}
