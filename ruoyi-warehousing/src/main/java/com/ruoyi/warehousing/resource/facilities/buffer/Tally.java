package com.ruoyi.warehousing.resource.facilities.buffer;



import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Tray;

import java.util.ArrayList;
import java.util.List;

public class Tally {
    private List<Point> points;
    private double torr;//托
    private List<Goods> goodsList;
    private List<Order> orders;
    private List<Tray> trays;//托盘
    private double pallet;//托盘量
    private double area;//区域面积
    private double tally_transverse;// 纵向数量
    private double tally_longitudinal;//横向数量
    public static double tally_channel = 0.6;//托盘间隙
    public static double forklift_channel=3;//叉车通道
    private int emp;//人员数量
    private double empCost;//人员成本
    private static double x=60;//初始化点的x坐标
    private static double y=40;//初始点的y坐标
    /**
     * 初始化分拣区位置
     * @param tally_transverse 横向托盘数量
     * @param tally_longitudinal 纵向托盘数量
     * @param channel_width 通道宽度
     * @param tray_clearance  托盘间隙
     */
    public void initTally(int tally_transverse,int tally_longitudinal,double channel_width,double tray_clearance,double tally_width,double tally_length){
        points = new ArrayList<>();
        for (int i=0;i<tally_transverse;i++){
            for (int j =0;j<tally_longitudinal;j++){
                Point point =new Point(x+i*(tally_width+channel_width)+tally_width/2,y+j*(tally_length+tray_clearance)+tally_length/2,1,0);
                points.add(point);
            }
        }
    }

    public void addTrays(Tray tray){
        trays = new ArrayList<>();
        for(Point point:points){
            if(point.getStatus()==0){
                point.setStatus(1);
                trays.add(tray);
            }
        }
    }
    public void removeTray(Tray tray){
        trays = new ArrayList<>();
        for(Point point:points){
            if(point.getStatus()==1&& WarehousingUtil.getDistance(point,tray.getPoint())==0){
                point.setStatus(0);
                trays.remove(tray);
            }
        }
    }

    public List<Tray> getTrays() {
        return trays;
    }

    public void setTrays(List<Tray> trays) {
        this.trays = trays;
    }

    public static double getTally_channel() {
        return tally_channel;
    }

    public static void setTally_channel(double tally_channel) {
        Tally.tally_channel = tally_channel;
    }

    public static double getForklift_channel() {
        return forklift_channel;
    }

    public static void setForklift_channel(double forklift_channel) {
        Tally.forklift_channel = forklift_channel;
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        Tally.x = x;
    }

    public static double getY() {
        return y;
    }

    public static void setY(double y) {
        Tally.y = y;
    }

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
