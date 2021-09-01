package com.ruoyi.production.form;

public class Production {
    private String code;//产品编码
    private String goods_code;// 物料编码
    private double goods_num;//物料数量


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    public double getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(double goods_num) {
        this.goods_num = goods_num;
    }
}
