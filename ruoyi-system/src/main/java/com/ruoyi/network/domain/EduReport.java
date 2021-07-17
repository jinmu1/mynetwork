package com.ruoyi.network.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 edu_report
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public class EduReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long batchId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** $column.columnComment */
    @Excel(name = "图片")
    private String classes;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 特征 */
    @Excel(name = "特征")
    private String feature;

    /** 特征属性1级 */
    @Excel(name = "特征属性1级")
    private String featureProperty1;

    /** 特征属性2级 */
    @Excel(name = "特征属性2级")
    private String featureProperty2;

    /** 特征属性3级 */
    @Excel(name = "特征属性3级")
    private String featureProperty3;

    /** 特征属性4级 */
    @Excel(name = "特征属性4级")
    private String featureProperty4;

    /** 问题 */
    @Excel(name = "问题")
    private String issue;

    /** 问题属性1级 */
    @Excel(name = "问题属性1级")
    private String issueProperty1;

    /** 问题属性2级 */
    @Excel(name = "问题属性2级")
    private String issueProperty2;

    /** 问题属性3级 */
    @Excel(name = "问题属性3级")
    private String issueProperty3;

    /** 问题属性4级 */
    @Excel(name = "问题属性4级")
    private String issueProperty4;

    /** 变量 */
    @Excel(name = "变量")
    private String variable;

    /** 建议 */
    @Excel(name = "建议")
    private String advice;

    /** 建议属性1级 */
    @Excel(name = "建议属性1级")
    private String adviceProperty1;

    /** 建议属性2级 */
    @Excel(name = "建议属性2级")
    private String adviceProperty2;

    /** 建议属性3级 */
    @Excel(name = "建议属性3级")
    private String adviceProperty3;

    /** 建议属性4级 */
    @Excel(name = "建议属性4级")
    private String adviceProperty4;

    /** 策略 */
    @Excel(name = "策略")
    private String strategy;

    /** 策略属性1级 */
    @Excel(name = "策略属性1级")
    private String strategyProperty1;

    /** 策略属性2级 */
    @Excel(name = "策略属性2级")
    private String strategyProperty2;

    /** 策略属性3级 */
    @Excel(name = "策略属性3级")
    private String strategyProperty3;

    /** 策略属性4级 */
    @Excel(name = "策略属性4级")
    private String strategyProperty4;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setClasses(String classes) 
    {
        this.classes = classes;
    }

    public String getClasses() 
    {
        return classes;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFeature(String feature) 
    {
        this.feature = feature;
    }

    public String getFeature() 
    {
        return feature;
    }
    public void setFeatureProperty1(String featureProperty1) 
    {
        this.featureProperty1 = featureProperty1;
    }

    public String getFeatureProperty1() 
    {
        return featureProperty1;
    }
    public void setFeatureProperty2(String featureProperty2) 
    {
        this.featureProperty2 = featureProperty2;
    }

    public String getFeatureProperty2() 
    {
        return featureProperty2;
    }
    public void setFeatureProperty3(String featureProperty3) 
    {
        this.featureProperty3 = featureProperty3;
    }

    public String getFeatureProperty3() 
    {
        return featureProperty3;
    }
    public void setFeatureProperty4(String featureProperty4) 
    {
        this.featureProperty4 = featureProperty4;
    }

    public String getFeatureProperty4() 
    {
        return featureProperty4;
    }
    public void setIssue(String issue) 
    {
        this.issue = issue;
    }

    public String getIssue() 
    {
        return issue;
    }
    public void setIssueProperty1(String issueProperty1) 
    {
        this.issueProperty1 = issueProperty1;
    }

    public String getIssueProperty1() 
    {
        return issueProperty1;
    }
    public void setIssueProperty2(String issueProperty2) 
    {
        this.issueProperty2 = issueProperty2;
    }

    public String getIssueProperty2() 
    {
        return issueProperty2;
    }
    public void setIssueProperty3(String issueProperty3) 
    {
        this.issueProperty3 = issueProperty3;
    }

    public String getIssueProperty3() 
    {
        return issueProperty3;
    }
    public void setIssueProperty4(String issueProperty4) 
    {
        this.issueProperty4 = issueProperty4;
    }

    public String getIssueProperty4() 
    {
        return issueProperty4;
    }
    public void setVariable(String variable) 
    {
        this.variable = variable;
    }

    public String getVariable() 
    {
        return variable;
    }
    public void setAdvice(String advice) 
    {
        this.advice = advice;
    }

    public String getAdvice() 
    {
        return advice;
    }
    public void setAdviceProperty1(String adviceProperty1) 
    {
        this.adviceProperty1 = adviceProperty1;
    }

    public String getAdviceProperty1() 
    {
        return adviceProperty1;
    }
    public void setAdviceProperty2(String adviceProperty2) 
    {
        this.adviceProperty2 = adviceProperty2;
    }

    public String getAdviceProperty2() 
    {
        return adviceProperty2;
    }
    public void setAdviceProperty3(String adviceProperty3) 
    {
        this.adviceProperty3 = adviceProperty3;
    }

    public String getAdviceProperty3() 
    {
        return adviceProperty3;
    }
    public void setAdviceProperty4(String adviceProperty4) 
    {
        this.adviceProperty4 = adviceProperty4;
    }

    public String getAdviceProperty4() 
    {
        return adviceProperty4;
    }
    public void setStrategy(String strategy) 
    {
        this.strategy = strategy;
    }

    public String getStrategy() 
    {
        return strategy;
    }
    public void setStrategyProperty1(String strategyProperty1) 
    {
        this.strategyProperty1 = strategyProperty1;
    }

    public String getStrategyProperty1() 
    {
        return strategyProperty1;
    }
    public void setStrategyProperty2(String strategyProperty2) 
    {
        this.strategyProperty2 = strategyProperty2;
    }

    public String getStrategyProperty2() 
    {
        return strategyProperty2;
    }
    public void setStrategyProperty3(String strategyProperty3) 
    {
        this.strategyProperty3 = strategyProperty3;
    }

    public String getStrategyProperty3() 
    {
        return strategyProperty3;
    }
    public void setStrategyProperty4(String strategyProperty4) 
    {
        this.strategyProperty4 = strategyProperty4;
    }

    public String getStrategyProperty4() 
    {
        return strategyProperty4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("title", getTitle())
            .append("img", getImg())
            .append("classes", getClasses())
            .append("type", getType())
            .append("feature", getFeature())
            .append("featureProperty1", getFeatureProperty1())
            .append("featureProperty2", getFeatureProperty2())
            .append("featureProperty3", getFeatureProperty3())
            .append("featureProperty4", getFeatureProperty4())
            .append("issue", getIssue())
            .append("issueProperty1", getIssueProperty1())
            .append("issueProperty2", getIssueProperty2())
            .append("issueProperty3", getIssueProperty3())
            .append("issueProperty4", getIssueProperty4())
            .append("variable", getVariable())
            .append("advice", getAdvice())
            .append("adviceProperty1", getAdviceProperty1())
            .append("adviceProperty2", getAdviceProperty2())
            .append("adviceProperty3", getAdviceProperty3())
            .append("adviceProperty4", getAdviceProperty4())
            .append("strategy", getStrategy())
            .append("strategyProperty1", getStrategyProperty1())
            .append("strategyProperty2", getStrategyProperty2())
            .append("strategyProperty3", getStrategyProperty3())
            .append("strategyProperty4", getStrategyProperty4())
            .toString();
    }
}
