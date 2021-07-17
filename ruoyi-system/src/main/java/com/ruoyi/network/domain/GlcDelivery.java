package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 原始出库数据对象 glc_delivery
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class GlcDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户ID */
    private Long batchId;

    /** 工厂代码 */
    @Excel(name = "工厂代码")
    private String factoryCode;

    /** 客户代码 */
    @Excel(name = "客户代码")
    private String customerCode;

    /** 送达方代码 */
    @Excel(name = "送达方代码")
    private String shipToParty;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String goodsCode;

    /** 物料描述 */
    @Excel(name = "物料描述")
    private String goodsName;

    /** 销售单价（元/件） */
    @Excel(name = "销售单价(元/件)")
    private String stockPrice;

    /** 过账数量 */
    @Excel(name = "过账数量")
    private String goodsNum;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 单位代码 */
    @Excel(name = "单位代码")
    private String unitCode;

    /** 过账数量（件） */
    @Excel(name = "过账数量(件)")
    private String goodsNum1;

    /** 过账数量（SKU） */
    @Excel(name = "过账数量(sku)")
    private String goodsNum2;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private String classify1;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private String classify2;

    /** 三级分类 */
    @Excel(name = "三级分类")
    private String classify3;

    /** 托盘装件数 */
    @Excel(name = "托盘装件数")
    private String palletCapacity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBatchId(Long batchId) 
    {
        this.batchId = batchId;
    }

    public Long getBatchId() 
    {
        return batchId;
    }
    public void setFactoryCode(String factoryCode) 
    {
        this.factoryCode = factoryCode;
    }

    public String getFactoryCode() 
    {
        return factoryCode;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setShipToParty(String shipToParty) 
    {
        this.shipToParty = shipToParty;
    }

    public String getShipToParty() 
    {
        return shipToParty;
    }
    public void setDeliveryDate(Date deliveryDate) 
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }

    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
    }
    public void setUnitCode(String unitCode) 
    {
        this.unitCode = unitCode;
    }

    public String getUnitCode() 
    {
        return unitCode;
    }

    public void setClassify1(String classify1) 
    {
        this.classify1 = classify1;
    }

    public String getClassify1() 
    {
        return classify1;
    }
    public void setClassify2(String classify2) 
    {
        this.classify2 = classify2;
    }

    public String getClassify2() 
    {
        return classify2;
    }
    public void setClassify3(String classify3) 
    {
        this.classify3 = classify3;
    }

    public String getClassify3() 
    {
        return classify3;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum1() {
        return goodsNum1;
    }

    public void setGoodsNum1(String goodsNum1) {
        this.goodsNum1 = goodsNum1;
    }

    public String getGoodsNum2() {
        return goodsNum2;
    }

    public void setGoodsNum2(String goodsNum2) {
        this.goodsNum2 = goodsNum2;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPalletCapacity() {
        return palletCapacity;
    }

    public void setPalletCapacity(String palletCapacity) {
        this.palletCapacity = palletCapacity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("factoryCode", getFactoryCode())
            .append("customerCode", getCustomerCode())
            .append("shipToParty", getShipToParty())
            .append("deliveryDate", getDeliveryDate())
            .append("orderCode", getOrderCode())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("stockPrice", getStockPrice())
            .append("goodsNum", getGoodsNum())
            .append("unitName", getUnitName())
            .append("unitCode", getUnitCode())
            .append("goodsNum1", getGoodsNum1())
            .append("goodsNum2", getGoodsNum2())
            .append("specification", getSpecification())
            .append("classify1", getClassify1())
            .append("classify2", getClassify2())
            .append("classify3", getClassify3())
            .append("palletCapacity", getPalletCapacity())
            .toString();
    }
}
