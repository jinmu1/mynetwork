package com.ruoyi.warehousing.resource.facilities.platform;

import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.form.Car;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

/**
 * 月台
 * @author jinmu
 */
public class Platform {
    private Car carLine;//车辆队列
    private String code;//编号
    private Point position;//位置
    private int emp;//月台人员数量
    private int sign;//签单
    private int status;//状态 0-无车辆等待 1-有车辆等待 -1车辆签单状态
    private List<Goods> goodsList;
    private double platform_num;//月台数量
    private double platform_area;//月台面积
    private double forklift;//卸货叉车
    private double empCost;//员工成本
    private double forkliftCost;//叉车成本
    private List<Car> cars;//停靠车辆编号
    private List<Point> points;
    public  double platform_width;//月台的宽度
    public  double platform_length;//单个月台的宽度 车辆宽度一般在1.8-2米之间
    private double platform_clearance;//月台间隙 3
    private static double x=0;//初始化点的x坐标
    private static double y=0;//初始点的y坐标
    /**
     * 按车位数和列数
     * @param parkNum
     * @param
     */
    private void initPoints(int parkNum){
        points = new ArrayList<>();
        for (int i=0;i<parkNum;i++){
            Point point = new Point(x+i*(platform_width+platform_clearance)+platform_width/2,y+platform_length/2,1,0);
            points.add(point);
        }
    }

    /**
     * 增加车辆
     * @param car
     */
    private void addCar(Car car){
        for (Point point:points ){
            if (point.getStatus()==0){
                point.setStatus(1);
                cars.add(car);
            }
        }
    }
    private void removeCar(Car car){
        for (Point point:points ){
            if (point.getStatus()==1&& WarehousingUtil.getDistance(car.getPoint(),point)==0){
                point.setStatus(1);
                cars.remove(car);
            }
        }
    }

    public Platform() {
    }

    public void addEmp(){
        this.emp++;
    }
    public void remoteEmp(){
        this.emp--;
    }
    public Platform(String code, int status,int emp,Point position) {
        this.code = code;
        this.status = status;
        this.emp = emp;
        this.position = position;
    }

    public double getPlatform_num() {
        return platform_num;
    }

    public void setPlatform_num(double platform_num) {
        this.platform_num = platform_num;
    }

    public double getPlatform_area() {
        return platform_area;
    }

    public double getForklift() {
        return forklift;
    }

    public void setForklift(double forklift) {
        this.forklift = forklift;
    }

    public void setPlatform_area(double platform_area) {
        this.platform_area = platform_area;
    }

    public Car getCarLine() {
        return carLine;
    }

    public void setCarLine(Car carLine) {
        this.carLine = carLine;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public void inSign() {
        this.sign = this.sign-1;
    }

    public double getEmpCost() {
        return empCost;
    }

    public void setEmpCost(double empCost) {
        this.empCost = empCost;
    }

    public double getForkliftCost() {
        return forkliftCost;
    }

    public void setForkliftCost(double forkliftCost) {
        this.forkliftCost = forkliftCost;
    }
}
