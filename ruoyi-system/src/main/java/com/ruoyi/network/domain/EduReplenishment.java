package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 补货数据对象 edu_replenishment
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduReplenishment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** 波次号 */
    @Excel(name = "波次号")
    private String batchCode;

    /** 任务产生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务产生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;


    /** 日期 */
    @JsonFormat(pattern = "HH:mm:ss")
    @Excel(name = "任务产生时间", width = 30, dateFormat = "HH:mm:ss")
    private Date createTime;

    /** 拣货库位 */
    @Excel(name = "拣货库位")
    private String storageLocation;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String goodsCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String goodsName;

    /** 待拣货数量 */
    @Excel(name = "待拣货数量")
    private String waitLocation;

    /** 规格 */
    @Excel(name = "规格")
    private Long pieceNum;

    /** 托盘规格 */
    @Excel(name = "托盘规格")
    private Long pulletNum;

    /** 温区 */
    @Excel(name = "温区")
    private String warmArea;


    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
    public void setBatchCode(String batchCode) 
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() 
    {
        return batchCode;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setStorageLocation(String storageLocation) 
    {
        this.storageLocation = storageLocation;
    }

    public String getStorageLocation() 
    {
        return storageLocation;
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
    public void setWaitLocation(String waitLocation) 
    {
        this.waitLocation = waitLocation;
    }

    public String getWaitLocation() 
    {
        return waitLocation;
    }
    public void setPieceNum(Long pieceNum) 
    {
        this.pieceNum = pieceNum;
    }

    public Long getPieceNum() 
    {
        return pieceNum;
    }
    public void setPulletNum(Long pulletNum) 
    {
        this.pulletNum = pulletNum;
    }

    public Long getPulletNum() 
    {
        return pulletNum;
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
            .append("batchCode", getBatchCode())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("storageLocation", getStorageLocation())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("waitLocation", getWaitLocation())
            .append("pieceNum", getPieceNum())
            .append("pulletNum", getPulletNum())
            .append("warmArea", getWarmArea())
            .toString();
    }
}
