package com.ruoyi.system.domain.mian;

/**
 * 理货区面积
 */
public class Tally {
    private double pallet;//托盘量
    private double area;//区域面积
    private double tally_transverse;// 纵向数量
    private double tally_longitudinal;//横向数量

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
}
