package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信支付订单对象 sys_order
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderCode;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 微信ID */
    @Excel(name = "微信ID")
    private String openId;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String producCode;

    /** 订单唯一标识 */
    @Excel(name = "订单唯一标识")
    private String outTradeNo;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private String account;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setProducCode(String producCode) 
    {
        this.producCode = producCode;
    }

    public String getProducCode() 
    {
        return producCode;
    }
    public void setOutTradeNo(String outTradeNo) 
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() 
    {
        return outTradeNo;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCode", getOrderCode())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("openId", getOpenId())
            .append("producCode", getProducCode())
            .append("outTradeNo", getOutTradeNo())
            .append("account", getAccount())
            .append("createTime", getCreateTime())
            .append("payTime", getPayTime())
            .toString();
    }
}
