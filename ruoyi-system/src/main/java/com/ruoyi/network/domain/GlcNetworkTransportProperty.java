package com.ruoyi.network.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 运输属性数据对象 glc_network_transport_property
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
public class GlcNetworkTransportProperty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** 起始点 */
    @Excel(name = "起始点")
    private String startingPoint;

    /** 终点 */
    @Excel(name = "终点")
    private String endPoint;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodName;

    /** 交通工具 */
    @Excel(name = "交通工具")
    private String transport;

    /** 运输方式 */
    @Excel(name = "运输方式")
    private String shippingMethod;

    /** 运输费率 */
    @Excel(name = "运输费率")
    private Double transportRate;

    /** 第几个阶段 */
    @Excel(name = "第几个阶段")
    private Integer type;

    /** $column.columnComment */
    private Long operatorId;

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
    public void setStartingPoint(String startingPoint) 
    {
        this.startingPoint = startingPoint;
    }

    public String getStartingPoint() 
    {
        return startingPoint;
    }
    public void setEndPoint(String endPoint) 
    {
        this.endPoint = endPoint;
    }

    public String getEndPoint() 
    {
        return endPoint;
    }
    public void setGoodName(String goodName) 
    {
        this.goodName = goodName;
    }

    public String getGoodName() 
    {
        return goodName;
    }
    public void setTransport(String transport) 
    {
        this.transport = transport;
    }

    public String getTransport() 
    {
        return transport;
    }
    public void setShippingMethod(String shippingMethod) 
    {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingMethod() 
    {
        return shippingMethod;
    }
    public void setTransportRate(Double transportRate)
    {
        this.transportRate = transportRate;
    }

    public Double getTransportRate()
    {
        return transportRate;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("startingPoint", getStartingPoint())
            .append("endPoint", getEndPoint())
            .append("goodName", getGoodName())
            .append("transport", getTransport())
            .append("shippingMethod", getShippingMethod())
            .append("transportRate", getTransportRate())
            .append("type", getType())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
