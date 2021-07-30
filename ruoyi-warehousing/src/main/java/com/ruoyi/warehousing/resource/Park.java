package com.ruoyi.warehousing.resource;

import java.util.List;

public class Park {
    private int num;//
    private List<Car> cars;//停靠车辆编号

    public Park(int num, List<Car> cars) {
        this.num = num;
        this.cars = cars;
    }

    public void add(Car car){
        cars.add(car);
        num++;
    }
    public void remove(Car car){
        cars.remove(car);
        num--;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Car getCar() {
        return cars.get(0);
    }
}
