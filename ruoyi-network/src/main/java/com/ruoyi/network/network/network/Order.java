package com.ruoyi.network.network.network;

import java.util.Date;

public class Order {
    private String orderCode;//订单编码
    //客户编码
    private String customerCode;
    //@TableField(value = "客户名称")
    private String customerName;
   // @TableField(value = "客户城市")
    private City customerCity;
   // @TableField(value = "物料编码")
    private String goodsCode;
   // @TableField(value = "区域")
    private String goodsArea;
    //@TableField(value = "产品数量")
    private double goodsNum;
    //@TableField(value = "产品单价")
    private String goodsPrice;
   // @TableField(value = "下单日期")
    private Date orderDate;
   // @TableField(value = "发货日期")
    private Date deliveryDate;


    public Order() {
    }

    public Order(String goodsCode,  double goodsNum) {
        this.goodsCode = goodsCode;
        this.goodsNum = goodsNum;
    }

    public Order(Date orderDate, String goodsCode, double goodsNum) {
        this.goodsCode = goodsCode;
        this.goodsNum = goodsNum;
        this.orderDate = orderDate;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public City getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(City customerCity) {
        this.customerCity = customerCity;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsArea() {
        return goodsArea;
    }

    public void setGoodsArea(String goodsArea) {
        this.goodsArea = goodsArea;
    }

    public double getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(double goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
