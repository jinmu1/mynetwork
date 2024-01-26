package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 glc_mdis
 * 
 * @author ruoyi
 * @date 2023-10-10
 */
public class GlcMdis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String arr;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lat;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lng;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String arr1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lat1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lng1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String distance;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String showt;

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setArr(String arr) 
    {
        this.arr = arr;
    }

    public String getArr() 
    {
        return arr;
    }
    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }
    public void setLng(String lng) 
    {
        this.lng = lng;
    }

    public String getLng() 
    {
        return lng;
    }
    public void setName1(String name1) 
    {
        this.name1 = name1;
    }

    public String getName1() 
    {
        return name1;
    }
    public void setArr1(String arr1) 
    {
        this.arr1 = arr1;
    }

    public String getArr1() 
    {
        return arr1;
    }
    public void setLat1(String lat1) 
    {
        this.lat1 = lat1;
    }

    public String getLat1() 
    {
        return lat1;
    }
    public void setLng1(String lng1) 
    {
        this.lng1 = lng1;
    }

    public String getLng1() 
    {
        return lng1;
    }
    public void setDistance(String distance) 
    {
        this.distance = distance;
    }

    public String getDistance() 
    {
        return distance;
    }
    public void setShowt(String showt) 
    {
        this.showt = showt;
    }

    public String getShowt() 
    {
        return showt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("arr", getArr())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("name1", getName1())
            .append("arr1", getArr1())
            .append("lat1", getLat1())
            .append("lng1", getLng1())
            .append("distance", getDistance())
            .append("showt", getShowt())
            .toString();
    }
}
