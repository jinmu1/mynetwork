package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设施数据对象 edu_facility
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduFacility extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** 设施功能区名称 */
    @Excel(name = "设施功能区名称")
    private String areasName;

    /** 设施功能 */
    @Excel(name = "设施功能")
    private String areasFunction;

    /** 设施功能面积 */
    @Excel(name = "设施功能面积")
    private String areasCover;

    /** 单价 */
    @Excel(name = "单价")
    private String prue;

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
    public void setAreasName(String areasName) 
    {
        this.areasName = areasName;
    }

    public String getAreasName() 
    {
        return areasName;
    }
    public void setAreasFunction(String areasFunction) 
    {
        this.areasFunction = areasFunction;
    }

    public String getAreasFunction() 
    {
        return areasFunction;
    }
    public void setAreasCover(String areasCover) 
    {
        this.areasCover = areasCover;
    }

    public String getAreasCover() 
    {
        return areasCover;
    }
    public void setPrue(String prue) 
    {
        this.prue = prue;
    }

    public String getPrue() 
    {
        return prue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("areasName", getAreasName())
            .append("areasFunction", getAreasFunction())
            .append("areasCover", getAreasCover())
            .append("prue", getPrue())
            .toString();
    }
}
