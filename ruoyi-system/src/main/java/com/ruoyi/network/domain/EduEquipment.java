package com.ruoyi.network.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备数据对象 edu_equipment
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** $column.columnComment */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** $column.columnComment */
    @Excel(name = "设备数量")
    private String equipmentNum;

    /** $column.columnComment */
    @Excel(name = "单个设备处理能力")
    private String equipmentAbility;

    /** 功能 */
    @Excel(name = "设备功能")
    private String equipmentFunction;

    /** $column.columnComment */
    @Excel(name = "设备单价")
    private String equipmentPrice;

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
    public void setEquipmentName(String equipmentName) 
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName() 
    {
        return equipmentName;
    }
    public void setEquipmentNum(String equipmentNum) 
    {
        this.equipmentNum = equipmentNum;
    }

    public String getEquipmentNum() 
    {
        return equipmentNum;
    }
    public void setEquipmentAbility(String equipmentAbility) 
    {
        this.equipmentAbility = equipmentAbility;
    }

    public String getEquipmentAbility() 
    {
        return equipmentAbility;
    }
    public void setEquipmentFunction(String equipmentFunction) 
    {
        this.equipmentFunction = equipmentFunction;
    }

    public String getEquipmentFunction() 
    {
        return equipmentFunction;
    }
    public void setEquipmentPrice(String equipmentPrice) 
    {
        this.equipmentPrice = equipmentPrice;
    }

    public String getEquipmentPrice() 
    {
        return equipmentPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("equipmentName", getEquipmentName())
            .append("equipmentNum", getEquipmentNum())
            .append("equipmentAbility", getEquipmentAbility())
            .append("equipmentFunction", getEquipmentFunction())
            .append("equipmentPrice", getEquipmentPrice())
            .toString();
    }
}
