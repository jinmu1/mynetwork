package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库对象 edu_receiving
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduReceiving extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表ID */
    private Long id;

    /** 项目号 */
    private Long batchId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 日期 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "时间", width = 30, dateFormat = "HH:mm:ss")
    private Date createTime;

    /** 供应商 */
    @Excel(name = "供应商")
    private String shipperCode;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderCode;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 数量 */
    @Excel(name = "数量")
    private Long goodsNum;

    /** 托盘数量 */
    @Excel(name = "托盘数量")
    private Long pulletNum;

    /** 件数 */
    @Excel(name = "件数")
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
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setShipperCode(String shipperCode) 
    {
        this.shipperCode = shipperCode;
    }


    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShipperCode()
    {
        return shipperCode;
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
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("shipperCode", getShipperCode())
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
