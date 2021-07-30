package com.ruoyi.warehousing.resource;

import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.form.Order;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.List;

/**
 *
 * car 车辆信息
 * @Auth: jinmu
 */
public class Car {
    private String carNo;//车辆编号
    private int  type;//车辆类型
    private List<Goods> goodsList;
    private int tora; //托数
    private Date arrinveTime;//到达时间
    private Date leaveTime;//离开时间
    public Car() {
        super();
    }

    public Car(String carNo, int type, List<Goods> goodsList) {
        this.carNo = carNo;
        this.type = type;
        this.goodsList = goodsList;
    }

    public Car(String carNo, int type, List<Goods> goodsList, Date arrinveTime,int tora) {
        this.carNo = carNo;
        this.type = type;
        this.goodsList = goodsList;
        this.arrinveTime = arrinveTime;
        this.tora = tora;
    }
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getArrinveTime() {
        return arrinveTime;
    }

    public void setArrinveTime(Date arrinveTime) {
        this.arrinveTime = arrinveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getTora() {
        return tora;
    }

    public void setTora(int tora) {
        this.tora = tora;
    }

    public void removeCar() {
        this.tora=this.tora-1;
    }
}
