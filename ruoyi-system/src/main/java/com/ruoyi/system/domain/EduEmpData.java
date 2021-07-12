package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人员数据导入对象 edu_emp_data
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public class EduEmpData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long batchId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String departmenName;

    /** 工作内容 */
    @Excel(name = "工作内容")
    private String dutyDescription;

    /** 人员数据 */
    @Excel(name = "人员数据")
    private Long employeeNumber;

    /** 人员工资 */
    @Excel(name = "人员工资")
    private Long employeeSalary;

    /** 单个人员处理能力 */
    @Excel(name = "单个人员处理能力")
    private Long processCapacity;

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
    public void setDepartmenName(String departmenName) 
    {
        this.departmenName = departmenName;
    }

    public String getDepartmenName() 
    {
        return departmenName;
    }
    public void setDutyDescription(String dutyDescription) 
    {
        this.dutyDescription = dutyDescription;
    }

    public String getDutyDescription() 
    {
        return dutyDescription;
    }
    public void setEmployeeNumber(Long employeeNumber) 
    {
        this.employeeNumber = employeeNumber;
    }

    public Long getEmployeeNumber() 
    {
        return employeeNumber;
    }
    public void setEmployeeSalary(Long employeeSalary) 
    {
        this.employeeSalary = employeeSalary;
    }

    public Long getEmployeeSalary() 
    {
        return employeeSalary;
    }
    public void setProcessCapacity(Long processCapacity) 
    {
        this.processCapacity = processCapacity;
    }

    public Long getProcessCapacity() 
    {
        return processCapacity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("departmenName", getDepartmenName())
            .append("dutyDescription", getDutyDescription())
            .append("employeeNumber", getEmployeeNumber())
            .append("employeeSalary", getEmployeeSalary())
            .append("processCapacity", getProcessCapacity())
            .toString();
    }
}
