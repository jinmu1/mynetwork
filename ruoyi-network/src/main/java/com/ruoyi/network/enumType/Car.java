package com.ruoyi.network.enumType;

/**
 * 车辆类型枚举类
 */
public enum Car {
    小车4米6("12"),
    中车7米2("24"),
    大车9米6("32"),
    超大车17米5("64");


    private String code;
    private Car(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
