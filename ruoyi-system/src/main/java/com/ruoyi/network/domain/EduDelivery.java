package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出库单对象 edu_delivery
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 批次号 */
    private Long batchId;

    /** 客户码 */
    @Excel(name = "客户")
    private String customerCode;

    /** 送达方 */
    @Excel(name = "送达方")
    private String shipToParty;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 交货时间 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "交货时间", width = 30, dateFormat = "HH:mm:ss")
    private Date deliveryTime;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderCode;

    /** 物料号 */
    @Excel(name = "物料号")
    private String goodsCode;

    /** 物料描述 */
    @Excel(name = "物料描述")
    private String goodsName;

    /** 物料数量 */
    @Excel(name = "物料数量")
    private Long goodsNum;

    /** 托盘数量 */
    @Excel(name = "托盘数量")
    private Long pulletNum;

    /** 规格 */
    @Excel(name = "规格")
    private Long pieceNum;

    /** 温区 */
    @Excel(name = "温区")
    private String warmArea;

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
    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
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
    public void setGoodsNum(Long goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public Long getGoodsNum() 
    {
        return goodsNum;
    }
    public void setPulletNum(Long pulletNum) 
    {
        this.pulletNum = pulletNum;
    }

    public Long getPulletNum() 
    {
        return pulletNum;
    }
    public void setPieceNum(Long pieceNum) 
    {
        this.pieceNum = pieceNum;
    }

    public Long getPieceNum() 
    {
        return pieceNum;
    }
    public void setWarmArea(String warmArea) 
    {
        this.warmArea = warmArea;
    }

    public String getWarmArea() 
    {
        return warmArea;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("customerCode", getCustomerCode())
            .append("shipToParty", getShipToParty())
            .append("deliveryDate", getDeliveryDate())
            .append("deliveryTime", getDeliveryTime())
            .append("orderCode", getOrderCode())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsNum", getGoodsNum())
            .append("pulletNum", getPulletNum())
            .append("pieceNum", getPieceNum())
            .append("warmArea", getWarmArea())
            .toString();
    }
}
