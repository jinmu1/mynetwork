package com.ruoyi.production.enumType;

public enum DistributionType {
    MilkRun("1"),
    VMI("2"),
    JIT("3"),
    Direct("4");


    private String code;
    private DistributionType(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
