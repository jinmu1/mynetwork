package com.ruoyi.network.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * glc对象 glc_network_supply_chain
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
public class GlcNetworkSupplyChain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 批次号 */
    private Long batchId;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private String orderType;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private Long orderQuantity;

    /** 售达方描述 */
    @Excel(name = "售达方描述")
    private String saleDafang;

    /** 售达方ID */
    @Excel(name = "售达方ID")
    private Long saleId;

    /** 售达方经度 */
    @Excel(name = "售达方经度")
    private String saleLng;

    /** 售达方纬度 */
    @Excel(name = "售达方纬度")
    private String saleLat;

    /** 产品组描述 */
    @Excel(name = "产品组描述")
    private String productGroup;

    /** 物料 */
    @Excel(name = "物料")
    private String material;

    /** 一级销售渠道 */
    @Excel(name = "一级销售渠道")
    private String primarySales;

    /** 二级销售渠道 */
    @Excel(name = "二级销售渠道")
    private String secondaryMarketing;

    /** 分中心 */
    @Excel(name = "分中心")
    private String branchCenter;

    /** 分拨中心ID */
    @Excel(name = "分拨中心ID")
    private Long branchId;

    /** 分拨中心经度 */
    @Excel(name = "分拨中心经度")
    private String branchLng;

    /** 分拨中心纬度 */
    @Excel(name = "分拨中心纬度")
    private String branchLat;

    /** 配送中心 */
    @Excel(name = "配送中心")
    private String deliveryCenter;

    /** 配送中心ID */
    @Excel(name = "配送中心ID")
    private Long deliveryId;

    /** 配送中心经度 */
    @Excel(name = "配送中心经度")
    private String deliveryLng;

    /** 配送中心纬度 */
    @Excel(name = "配送中心纬度")
    private String deliveryLat;

    /** 工厂名称 */
    @Excel(name = "工厂名称")
    private String factoryName;

    /** 要求到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "要求到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requestArrival;

    /** 承诺到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "承诺到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date promiseArrival;

    /** 配车制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "配车制单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date preparationCar;

    /** 提货点 */
    @Excel(name = "提货点")
    private String pickUp;

    /** 车辆计划提货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆计划提货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vehicleSchedule;

    /** 车辆实际到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆实际到达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualArrivalVehicle;

    /** 车辆及时到位数量 */
    @Excel(name = "车辆及时到位数量")
    private Long numberVehicles;

    /** 装车实际开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "装车实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartLoading;

    /** 装车实际结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "装车实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndLoading;

    /** 装车及时数量 */
    @Excel(name = "装车及时数量")
    private Long loadingQuantity;

    /** 工厂实际发货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工厂实际发货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualShipment;

    /** 工厂实际发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工厂实际发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualDelivery;

    /** 预计到分中心 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计到分中心", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expectedCentre;

    /** 车队到分中心登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车队到分中心登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date carArrive;

    /** 及时卸车入库数量 */
    @Excel(name = "及时卸车入库数量")
    private Long unloadingQuantity;

    /** 货到分中心入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "货到分中心入库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageGoods;

    /** 分中心提货单过账日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "分中心提货单过账日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date centresPosting;

    /** 客户实际签收日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "客户实际签收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualSignature;

    /** 签收数量 */
    @Excel(name = "签收数量")
    private Long signQuantity;

    /** 返单数量 */
    @Excel(name = "返单数量")
    private Long returnQuantity;

    /** 运输延误天数 */
    @Excel(name = "运输延误天数")
    private Long delayTransport;

    /** 到货延误天数 */
    @Excel(name = "到货延误天数")
    private Long delayArrival;

    /**  */
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
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderType() 
    {
        return orderType;
    }
    public void setOrderQuantity(Long orderQuantity) 
    {
        this.orderQuantity = orderQuantity;
    }

    public Long getOrderQuantity() 
    {
        return orderQuantity;
    }
    public void setSaleDafang(String saleDafang) 
    {
        this.saleDafang = saleDafang;
    }

    public String getSaleDafang() 
    {
        return saleDafang;
    }
    public void setSaleId(Long saleId) 
    {
        this.saleId = saleId;
    }

    public Long getSaleId() 
    {
        return saleId;
    }
    public void setSaleLng(String saleLng) 
    {
        this.saleLng = saleLng;
    }

    public String getSaleLng() 
    {
        return saleLng;
    }
    public void setSaleLat(String saleLat) 
    {
        this.saleLat = saleLat;
    }

    public String getSaleLat() 
    {
        return saleLat;
    }
    public void setProductGroup(String productGroup) 
    {
        this.productGroup = productGroup;
    }

    public String getProductGroup() 
    {
        return productGroup;
    }
    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getMaterial() 
    {
        return material;
    }
    public void setPrimarySales(String primarySales) 
    {
        this.primarySales = primarySales;
    }

    public String getPrimarySales() 
    {
        return primarySales;
    }
    public void setSecondaryMarketing(String secondaryMarketing) 
    {
        this.secondaryMarketing = secondaryMarketing;
    }

    public String getSecondaryMarketing() 
    {
        return secondaryMarketing;
    }
    public void setBranchCenter(String branchCenter) 
    {
        this.branchCenter = branchCenter;
    }

    public String getBranchCenter() 
    {
        return branchCenter;
    }
    public void setBranchId(Long branchId) 
    {
        this.branchId = branchId;
    }

    public Long getBranchId() 
    {
        return branchId;
    }
    public void setBranchLng(String branchLng) 
    {
        this.branchLng = branchLng;
    }

    public String getBranchLng() 
    {
        return branchLng;
    }
    public void setBranchLat(String branchLat) 
    {
        this.branchLat = branchLat;
    }

    public String getBranchLat() 
    {
        return branchLat;
    }
    public void setDeliveryCenter(String deliveryCenter) 
    {
        this.deliveryCenter = deliveryCenter;
    }

    public String getDeliveryCenter() 
    {
        return deliveryCenter;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setDeliveryLng(String deliveryLng) 
    {
        this.deliveryLng = deliveryLng;
    }

    public String getDeliveryLng() 
    {
        return deliveryLng;
    }
    public void setDeliveryLat(String deliveryLat) 
    {
        this.deliveryLat = deliveryLat;
    }

    public String getDeliveryLat() 
    {
        return deliveryLat;
    }
    public void setFactoryName(String factoryName) 
    {
        this.factoryName = factoryName;
    }

    public String getFactoryName() 
    {
        return factoryName;
    }
    public void setRequestArrival(Date requestArrival) 
    {
        this.requestArrival = requestArrival;
    }

    public Date getRequestArrival() 
    {
        return requestArrival;
    }
    public void setPromiseArrival(Date promiseArrival) 
    {
        this.promiseArrival = promiseArrival;
    }

    public Date getPromiseArrival() 
    {
        return promiseArrival;
    }
    public void setPreparationCar(Date preparationCar) 
    {
        this.preparationCar = preparationCar;
    }

    public Date getPreparationCar() 
    {
        return preparationCar;
    }
    public void setPickUp(String pickUp) 
    {
        this.pickUp = pickUp;
    }

    public String getPickUp() 
    {
        return pickUp;
    }
    public void setVehicleSchedule(Date vehicleSchedule) 
    {
        this.vehicleSchedule = vehicleSchedule;
    }

    public Date getVehicleSchedule() 
    {
        return vehicleSchedule;
    }
    public void setActualArrivalVehicle(Date actualArrivalVehicle) 
    {
        this.actualArrivalVehicle = actualArrivalVehicle;
    }

    public Date getActualArrivalVehicle() 
    {
        return actualArrivalVehicle;
    }
    public void setNumberVehicles(Long numberVehicles) 
    {
        this.numberVehicles = numberVehicles;
    }

    public Long getNumberVehicles() 
    {
        return numberVehicles;
    }
    public void setActualStartLoading(Date actualStartLoading) 
    {
        this.actualStartLoading = actualStartLoading;
    }

    public Date getActualStartLoading() 
    {
        return actualStartLoading;
    }
    public void setActualEndLoading(Date actualEndLoading) 
    {
        this.actualEndLoading = actualEndLoading;
    }

    public Date getActualEndLoading() 
    {
        return actualEndLoading;
    }
    public void setLoadingQuantity(Long loadingQuantity) 
    {
        this.loadingQuantity = loadingQuantity;
    }

    public Long getLoadingQuantity() 
    {
        return loadingQuantity;
    }
    public void setActualShipment(Date actualShipment) 
    {
        this.actualShipment = actualShipment;
    }

    public Date getActualShipment() 
    {
        return actualShipment;
    }
    public void setActualDelivery(Date actualDelivery) 
    {
        this.actualDelivery = actualDelivery;
    }

    public Date getActualDelivery() 
    {
        return actualDelivery;
    }
    public void setExpectedCentre(Date expectedCentre) 
    {
        this.expectedCentre = expectedCentre;
    }

    public Date getExpectedCentre() 
    {
        return expectedCentre;
    }
    public void setCarArrive(Date carArrive) 
    {
        this.carArrive = carArrive;
    }

    public Date getCarArrive() 
    {
        return carArrive;
    }
    public void setUnloadingQuantity(Long unloadingQuantity) 
    {
        this.unloadingQuantity = unloadingQuantity;
    }

    public Long getUnloadingQuantity() 
    {
        return unloadingQuantity;
    }
    public void setStorageGoods(Date storageGoods) 
    {
        this.storageGoods = storageGoods;
    }

    public Date getStorageGoods() 
    {
        return storageGoods;
    }
    public void setCentresPosting(Date centresPosting) 
    {
        this.centresPosting = centresPosting;
    }

    public Date getCentresPosting() 
    {
        return centresPosting;
    }
    public void setActualSignature(Date actualSignature) 
    {
        this.actualSignature = actualSignature;
    }

    public Date getActualSignature() 
    {
        return actualSignature;
    }
    public void setSignQuantity(Long signQuantity) 
    {
        this.signQuantity = signQuantity;
    }

    public Long getSignQuantity() 
    {
        return signQuantity;
    }
    public void setReturnQuantity(Long returnQuantity) 
    {
        this.returnQuantity = returnQuantity;
    }

    public Long getReturnQuantity() 
    {
        return returnQuantity;
    }
    public void setDelayTransport(Long delayTransport) 
    {
        this.delayTransport = delayTransport;
    }

    public Long getDelayTransport() 
    {
        return delayTransport;
    }
    public void setDelayArrival(Long delayArrival) 
    {
        this.delayArrival = delayArrival;
    }

    public Long getDelayArrival() 
    {
        return delayArrival;
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
            .append("orderType", getOrderType())
            .append("orderQuantity", getOrderQuantity())
            .append("saleDafang", getSaleDafang())
            .append("saleId", getSaleId())
            .append("saleLng", getSaleLng())
            .append("saleLat", getSaleLat())
            .append("productGroup", getProductGroup())
            .append("material", getMaterial())
            .append("primarySales", getPrimarySales())
            .append("secondaryMarketing", getSecondaryMarketing())
            .append("branchCenter", getBranchCenter())
            .append("branchId", getBranchId())
            .append("branchLng", getBranchLng())
            .append("branchLat", getBranchLat())
            .append("deliveryCenter", getDeliveryCenter())
            .append("deliveryId", getDeliveryId())
            .append("deliveryLng", getDeliveryLng())
            .append("deliveryLat", getDeliveryLat())
            .append("factoryName", getFactoryName())
            .append("requestArrival", getRequestArrival())
            .append("promiseArrival", getPromiseArrival())
            .append("preparationCar", getPreparationCar())
            .append("pickUp", getPickUp())
            .append("vehicleSchedule", getVehicleSchedule())
            .append("actualArrivalVehicle", getActualArrivalVehicle())
            .append("numberVehicles", getNumberVehicles())
            .append("actualStartLoading", getActualStartLoading())
            .append("actualEndLoading", getActualEndLoading())
            .append("loadingQuantity", getLoadingQuantity())
            .append("actualShipment", getActualShipment())
            .append("actualDelivery", getActualDelivery())
            .append("expectedCentre", getExpectedCentre())
            .append("carArrive", getCarArrive())
            .append("unloadingQuantity", getUnloadingQuantity())
            .append("storageGoods", getStorageGoods())
            .append("centresPosting", getCentresPosting())
            .append("actualSignature", getActualSignature())
            .append("signQuantity", getSignQuantity())
            .append("returnQuantity", getReturnQuantity())
            .append("delayTransport", getDelayTransport())
            .append("delayArrival", getDelayArrival())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
