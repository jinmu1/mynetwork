package com.ruoyi.warehousing.resource.facilities.platform;

import com.ruoyi.warehousing.form.Car;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;

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
}
