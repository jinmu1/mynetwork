package com.ruoyi.network.domain;

import java.io.Serializable;

/**
 * 这个类是用来计算EOQ数据的类
 * 具体字段需要看相关文档
 * 相关文档在公司资料里
 * by:jinmu
 */
public class EduEOQ implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;
    private Long batch_id;

    private String goods_name;

    private String stock_price;

    private String unit_storage_cost;
    private String single_order_cost;
    private String total_delivery;
    private String frequency_now;
    private String total_receiving;
    private String average_inventory;
    private String EOQ;
    private String ordering_cost;
    private String inventory_cost;
    private String total_cost;
    private String frequency;
    private String cycle;
    private String cycle1;
    private String cycle2;
    private String orderingcost_now;
    private String inventory_cost_now;
    private String total_cost_now;
    private String deviation1;
    private String deviation2;
    private String deviation_total;
    private String unit_storage_cost2;
    private String Shortage_costs;
    private String productivity;
    private String EOQ1;
    private String EOQ2;
    private String EOQ_1;
    private String EOQ_2;
    private String quantity1;
    private String quantity2;
    private String quantity_final;
    private String frequency1;
    private String frequency_final;
    private String frequency2;
    private String inventory_cost_Q;
    private String price_Q;
    private String total_cost_Q1;
    private String total_cost_Q2;
    private String total_cost_EOQ;

    public Long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Long batch_id) {
        this.batch_id = batch_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    public String getUnit_storage_cost() {
        return unit_storage_cost;
    }

    public void setUnit_storage_cost(String unit_storage_cost) {
        this.unit_storage_cost = unit_storage_cost;
    }

    public String getSingle_order_cost() {
        return single_order_cost;
    }

    public void setSingle_order_cost(String single_order_cost) {
        this.single_order_cost = single_order_cost;
    }

    public String getTotal_delivery() {
        return total_delivery;
    }

    public void setTotal_delivery(String total_delivery) {
        this.total_delivery = total_delivery;
    }

    public String getFrequency_now() {
        return frequency_now;
    }

    public void setFrequency_now(String frequency_now) {
        this.frequency_now = frequency_now;
    }

    public String getTotal_receiving() {
        return total_receiving;
    }

    public void setTotal_receiving(String total_receiving) {
        this.total_receiving = total_receiving;
    }

    public String getAverage_inventory() {
        return average_inventory;
    }

    public void setAverage_inventory(String average_inventory) {
        this.average_inventory = average_inventory;
    }

    public String getEOQ() {
        return EOQ;
    }

    public void setEOQ(String EOQ) {
        this.EOQ = EOQ;
    }

    public String getOrdering_cost() {
        return ordering_cost;
    }

    public void setOrdering_cost(String ordering_cost) {
        this.ordering_cost = ordering_cost;
    }

    public String getInventory_cost() {
        return inventory_cost;
    }

    public void setInventory_cost(String inventory_cost) {
        this.inventory_cost = inventory_cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getOrderingcost_now() {
        return orderingcost_now;
    }

    public void setOrderingcost_now(String orderingcost_now) {
        this.orderingcost_now = orderingcost_now;
    }

    public String getInventory_cost_now() {
        return inventory_cost_now;
    }

    public void setInventory_cost_now(String inventory_cost_now) {
        this.inventory_cost_now = inventory_cost_now;
    }

    public String getTotal_cost_now() {
        return total_cost_now;
    }

    public void setTotal_cost_now(String total_cost_now) {
        this.total_cost_now = total_cost_now;
    }

    public String getDeviation1() {
        return deviation1;
    }

    public void setDeviation1(String deviation1) {
        this.deviation1 = deviation1;
    }

    public String getDeviation2() {
        return deviation2;
    }

    public void setDeviation2(String deviation2) {
        this.deviation2 = deviation2;
    }

    public String getDeviation_total() {
        return deviation_total;
    }

    public void setDeviation_total(String deviation_total) {
        this.deviation_total = deviation_total;
    }

    public String getUnit_storage_cost2() {
        return unit_storage_cost2;
    }

    public void setUnit_storage_cost2(String unit_storage_cost2) {
        this.unit_storage_cost2 = unit_storage_cost2;
    }

    public String getShortage_costs() {
        return Shortage_costs;
    }

    public void setShortage_costs(String shortage_costs) {
        Shortage_costs = shortage_costs;
    }

    public String getProductivity() {
        return productivity;
    }

    public void setProductivity(String productivity) {
        this.productivity = productivity;
    }

    public String getEOQ1() {
        return EOQ1;
    }

    public void setEOQ1(String EOQ1) {
        this.EOQ1 = EOQ1;
    }

    public String getEOQ2() {
        return EOQ2;
    }

    public void setEOQ2(String EOQ2) {
        this.EOQ2 = EOQ2;
    }

    public String getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(String quantity1) {
        this.quantity1 = quantity1;
    }

    public String getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(String quantity2) {
        this.quantity2 = quantity2;
    }

    public String getQuantity_final() {
        return quantity_final;
    }

    public void setQuantity_final(String quantity_final) {
        this.quantity_final = quantity_final;
    }

    public String getFrequency1() {
        return frequency1;
    }

    public void setFrequency1(String frequency1) {
        this.frequency1 = frequency1;
    }

    public String getFrequency_final() {
        return frequency_final;
    }

    public void setFrequency_final(String frequency_final) {
        this.frequency_final = frequency_final;
    }

    public String getFrequency2() {
        return frequency2;
    }

    public void setFrequency2(String frequency2) {
        this.frequency2 = frequency2;
    }

    public String getInventory_cost_Q() {
        return inventory_cost_Q;
    }

    public void setInventory_cost_Q(String inventory_cost_Q) {
        this.inventory_cost_Q = inventory_cost_Q;
    }

    public String getPrice_Q() {
        return price_Q;
    }

    public void setPrice_Q(String price_Q) {
        this.price_Q = price_Q;
    }

    public String getTotal_cost_Q1() {
        return total_cost_Q1;
    }

    public void setTotal_cost_Q1(String total_cost_Q1) {
        this.total_cost_Q1 = total_cost_Q1;
    }

    public String getTotal_cost_Q2() {
        return total_cost_Q2;
    }

    public void setTotal_cost_Q2(String total_cost_Q2) {
        this.total_cost_Q2 = total_cost_Q2;
    }

    public String getTotal_cost_EOQ() {
        return total_cost_EOQ;
    }

    public void setTotal_cost_EOQ(String total_cost_EOQ) {
        this.total_cost_EOQ = total_cost_EOQ;
    }

    public String getCycle1() {
        return cycle1;
    }

    public void setCycle1(String cycle1) {
        this.cycle1 = cycle1;
    }

    public String getCycle2() {
        return cycle2;
    }

    public void setCycle2(String cycle2) {
        this.cycle2 = cycle2;
    }

    public String getEOQ_1() {
        return EOQ_1;
    }

    public void setEOQ_1(String EOQ_1) {
        this.EOQ_1 = EOQ_1;
    }

    public String getEOQ_2() {
        return EOQ_2;
    }

    public void setEOQ_2(String EOQ_2) {
        this.EOQ_2 = EOQ_2;
    }
}
