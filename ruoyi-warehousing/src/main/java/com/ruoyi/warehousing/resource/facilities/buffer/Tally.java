package com.ruoyi.warehousing.resource.facilities.buffer;



import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.queue.Point;

import java.util.ArrayList;
import java.util.List;

public class Tally {
    private List<Point> points;
    private double torr;//托
    private List<Goods> goodsList;
    private List<Order> orders;
    private double pallet;//托盘量
    private double area;//区域面积
    private double tally_transverse;// 纵向数量
    private double tally_longitudinal;//横向数量
    private int emp;//人员数量
    private double empCost;//人员成本

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public double getEmpCost() {
        return empCost;
    }

    public void setEmpCost(double empCost) {
        this.empCost = empCost;
    }

    public Tally(double torr) {
        this.torr=torr;
        this.goodsList = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    public Tally() {
        this.goodsList = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public double getPallet() {
        return pallet;
    }

    public void setPallet(double pallet) {
        this.pallet = pallet;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getTally_transverse() {
        return tally_transverse;
    }

    public void setTally_transverse(double tally_transverse) {
        this.tally_transverse = tally_transverse;
    }

    public double getTally_longitudinal() {
        return tally_longitudinal;
    }

    public void setTally_longitudinal(double tally_longitudinal) {
        this.tally_longitudinal = tally_longitudinal;
    }

    public double getTorr() {
        return torr;
    }

    public void setTorr(double torr) {
        this.torr = torr;
    }

    public void deal(){
        this.torr--;
    }
    public void add(){
        this.torr++;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
    public void add(List<Goods> goods){

        this.goodsList.addAll(goods);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
