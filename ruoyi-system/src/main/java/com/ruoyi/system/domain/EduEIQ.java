package com.ruoyi.system.domain;

public class EduEIQ {
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;
    private int anInt;//序号
    private String date;//日期
    private String goods_name;//商品名称
    private String goods_code;//物料编号
    private String goods_num;
    private String order_num;//订单数
    private String order_num1;//订单行数
    private String order_code; //订单编号
    private String times;//次数
    private String total_num;//总箱数
    private String delivery_num;//出库总箱数
    private String odd_num;//拆零折合箱数
    private String category_number;//品种数
    private String delivery_percent;//百分比
    private String customer_num; //客户数量
    private String ship_num;//送达方

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_num1() {
        return order_num1;
    }

    public void setOrder_num1(String order_num1) {
        this.order_num1 = order_num1;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getDelivery_num() {
        return delivery_num;
    }

    public void setDelivery_num(String delivery_num) {
        this.delivery_num = delivery_num;
    }

    public String getOdd_num() {
        return odd_num;
    }

    public void setOdd_num(String odd_num) {
        this.odd_num = odd_num;
    }

    public String getCategory_number() {
        return category_number;
    }

    public void setCategory_number(String category_number) {
        this.category_number = category_number;
    }

    public String getDelivery_percent() {
        return delivery_percent;
    }

    public void setDelivery_percent(String delivery_percent) {
        this.delivery_percent = delivery_percent;
    }

    public String getCustomer_num() {
        return customer_num;
    }

    public void setCustomer_num(String customer_num) {
        this.customer_num = customer_num;
    }

    public String getShip_num() {
        return ship_num;
    }

    public void setShip_num(String ship_num) {
        this.ship_num = ship_num;
    }
}
