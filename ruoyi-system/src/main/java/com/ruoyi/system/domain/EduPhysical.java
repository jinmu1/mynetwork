package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存数据对象 edu_physical
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduPhysical extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date physicalDate;

    /** 货物编码 */
    @Excel(name = "货物编码")
    private String goodsCode;

    /** 描述 */
    @Excel(name = "描述")
    private String goodsName;

    /** 总库存 */
    @Excel(name = "总库存")
    private Long deliveryTotal;

    /** 区域 */
    private Long pulletNum;

    /** 托盘规格 */
    @Excel(name = "托盘规格")
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
    public void setPhysicalDate(Date physicalDate) 
    {
        this.physicalDate = physicalDate;
    }

    public Date getPhysicalDate() 
    {
        return physicalDate;
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
    public void setDeliveryTotal(Long deliveryTotal) 
    {
        this.deliveryTotal = deliveryTotal;
    }

    public Long getDeliveryTotal() 
    {
        return deliveryTotal;
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
            .append("physicalDate", getPhysicalDate())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("deliveryTotal", getDeliveryTotal())
            .append("pulletNum", getPulletNum())
            .append("pieceNum", getPieceNum())
            .append("warmArea", getWarmArea())
            .toString();
    }
}
