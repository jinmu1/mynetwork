package com.ruoyi.system.domain;

import java.io.Serializable;

public class EduPCB implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;
    private String product_unit; // 单位（瓶包 ）
    private String specification; //规格
    private String pallet_capacity;//托盘装件数
    private String P;
    private String C;
    private String B;
    private String result1;
    private String result2;
    private String result3;
    private String date;
    private String num;
    private String type;
    private String goods_name;//商品名称
    private String goods_code;//商品代码
    private String unit_cases;//单位（件）
    private String pallet_num;//托盘数量
    private String goods_num2;//过账数量（suk）
    private String goods_num1;//过账数量(件)
    private String cases_num1;//托外单件数量
    private String cases_num2;//托外单品数量
    private String pallet_num1;//整拖数量
    private String cases_num3;//托外整件数量
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCases_num3() {
        return cases_num3;
    }

    public void setCases_num3(String cases_num3) {
        this.cases_num3 = cases_num3;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPallet_capacity() {
        return pallet_capacity;
    }

    public void setPallet_capacity(String pallet_capacity) {
        this.pallet_capacity = pallet_capacity;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getUnit_cases() {
        return unit_cases;
    }

    public void setUnit_cases(String unit_cases) {
        this.unit_cases = unit_cases;
    }

    public String getPallet_num() {
        return pallet_num;
    }

    public void setPallet_num(String pallet_num) {
        this.pallet_num = pallet_num;
    }

    public String getGoods_num2() {
        return goods_num2;
    }

    public void setGoods_num2(String goods_num2) {
        this.goods_num2 = goods_num2;
    }

    public String getGoods_num1() {
        return goods_num1;
    }

    public void setGoods_num1(String goods_num1) {
        this.goods_num1 = goods_num1;
    }

    public String getCases_num1() {
        return cases_num1;
    }

    public void setCases_num1(String cases_num) {
        this.cases_num1 = cases_num;
    }

    public String getPallet_num1() {
        return pallet_num1;
    }

    public void setPallet_num1(String pallet_num1) {
        this.pallet_num1 = pallet_num1;
    }

    public String getCases_num2() {
        return cases_num2;
    }

    public void setCases_num2(String cases_num2) {
        this.cases_num2 = cases_num2;
    }
}
