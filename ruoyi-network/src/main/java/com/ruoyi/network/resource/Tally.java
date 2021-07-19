package com.ruoyi.network.resource;

import com.ruoyi.network.enumType.StorageType;

import static com.ruoyi.network.resource.Platform.getPlatform;

/**
 * 理货区面积
 */
public class Tally {
    private double pallet;//托盘量
    private double area;//区域面积
    private double tally_transverse;// 纵向数量
    private double tally_longitudinal;//横向数量
    protected static double platform_width = 3.5;//月台的宽度
    protected static double tally_batch=4;//理货波次
    protected static double tally_rate=1.5;//高峰理货系数
    protected static double tray_length = 1.2;//托盘长度
    protected static double tray_width = 1;//托盘宽度
    protected static double tray_clearance = 0.1;//托盘间隙
    protected static double tally_channel = 0.6;//托盘间隙
    protected static double forklift_channel=3;//叉车通道
    protected static double shelf_channel = 1;//人员拣货通道
    protected static double tray_price=280;//托盘-塑料（带RFID)
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


    /**
     * 计算理货区面积
     *
     */
    public static Tally getTally(double  total,String carType){
        double pallet_num = total*tally_rate/tally_batch;//理货平均托盘量

        double tally_transverse = getPlatform(total,carType).getPlatform_num();//纵向数量
        if (tally_transverse>pallet_num){
            tally_transverse = 1;
        }
        double tally_longitudinal= Math.ceil(pallet_num/tally_transverse);//横向数量
        double tally_area = tally_longitudinal*(tray_length+tray_clearance)*(tray_width+tally_channel+3)*tally_transverse;//理货区面积
        Tally tally = new Tally();
        tally.setPallet(pallet_num);
        tally.setArea(tally_area);
        tally.setTally_longitudinal(tally_longitudinal);
        tally.setTally_transverse(tally_transverse);
        return tally;
    }

    /***
     * 计算地堆存储区面积
     */
    public static Storage getHeapStorage(double total){
        double area = total*tray_width*tray_length;//地堆区面积
        Storage storage = new Storage();
        storage.setType(StorageType.地堆区域.getCode());
        storage.setPrice(total*tray_price);
        storage.setArea(area);
        return storage;
    }
}
