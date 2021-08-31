package com.ruoyi.production.resource.facilities.buffer;


import com.ruoyi.production.form.Car;
import com.ruoyi.production.queue.Point;
import com.ruoyi.warehousing.action.WarehousingUtil;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private int num;//
    private List<com.ruoyi.production.form.Car> cars =new ArrayList<>();//停靠车辆编号
    private List<com.ruoyi.production.queue.Point> points;
    private static double park_width = 3.5;
    private static double park_Length = 9.6;

    /**
     * 按车位数和列数
     * @param parkNum
     * @param line
     */
    public void initPoints(int parkNum,int line){
        points = new ArrayList<>();
       for (int i=0;i<parkNum/line;i++){
           for (int j=0;j<line;j++) {
               com.ruoyi.production.queue.Point point = new com.ruoyi.production.queue.Point(i*park_width+park_width/2,j*park_Length+park_Length/2,1,0);
               points.add(point);
           }
       }
    }

    public Park() {

    }

    /**
     * 增加车辆
     * @param car
     */
    private void addCar(com.ruoyi.production.form.Car car){
        for (com.ruoyi.production.queue.Point point:points ){
            if (point.getStatus()==0){
                point.setStatus(1);
                cars.add(car);
            }
        }
    }
    private void removeCar(com.ruoyi.production.form.Car car){
        for (com.ruoyi.production.queue.Point point:points ){
            if (point.getStatus()==1&& WarehousingUtil.getDistance(car.getPoint(),point)==0){
                point.setStatus(1);
                cars.remove(car);
            }
        }
    }

    public List<com.ruoyi.production.queue.Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Park(int num, List<com.ruoyi.production.form.Car> cars) {
        this.num = num;
        this.cars = cars;
    }

    public void add(com.ruoyi.production.form.Car car){
        this.cars.add(car);
        num++;
    }
    public void remove(com.ruoyi.production.form.Car car){
        cars.remove(car);
        num--;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<com.ruoyi.production.form.Car> getCars() {
        return cars;
    }

    public void setCars(List<com.ruoyi.production.form.Car> cars) {
        this.cars = cars;
    }

    public Car getCar() {

        return cars.get(0);
    }
}
