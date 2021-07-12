package com.ruoyi.system.domain.mian;

/**
 * 车辆类型枚举类
 */
public enum Car {
    小车4米6("6"),
    中车7米2("12"),
    大车9米6("16"),
    超大车17米5("32");


    private String code;
    private Car(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
