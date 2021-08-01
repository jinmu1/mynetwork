package com.ruoyi.warehousing.resource.equipment;

import com.ruoyi.warehousing.form.Cargo;

/**
 * 堆垛机使用类
 */
public class Stacker {
    private String eqmNo;;//堆垛机编号
    private int status ;//忙闲状态
    private double xspeed;//水平位移速度
    private double yspped;//垂直位移速度
    private Cargo target;//堆垛机目标货位

    public Stacker(String eqmNo, double xspeed, double yspped) {
        this.eqmNo = eqmNo;
        this.xspeed = xspeed;
        this.yspped = yspped;
    }

    public String getEqmNo() {
        return eqmNo;
    }

    public void setEqmNo(String eqmNo) {
        this.eqmNo = eqmNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public double getYspped() {
        return yspped;
    }

    public void setYspped(double yspped) {
        this.yspped = yspped;
    }

    public Cargo getCargo() {
        return target;
    }

    public void setCargo(Cargo cargo) {
        this.target = cargo;
    }
}
