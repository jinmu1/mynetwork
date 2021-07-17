package com.ruoyi.network.result;
/**
 *网络规划结果输出
 */
public class Result {
    private String city;//RDC
    private String range;//覆盖范围
    private  double transportCost;//运输成本输出
    private double  storageCost;//仓储成本输出
    private  double buildCost;//仓库建设成本输出
    private double  inventoryCost;//库存持有成本输出
    private double  all;//总成本输出
    private double rate;//总物流费率
    private double emp;//总的仓库人数
    private double area;//总的网络面积
    private double storage_area;
    private double storage;//仓储量
    private double car;//车辆数
    private double order_rate;//订单满足率
    private double replenishment_rate;//补货及时率
    private double distribution_rate;//配送效率
    private double storage_rate;//仓库作业效率
    private double inventory_num;//存储能力
    private double throughput_num;//吞吐能力
    private double sales_account;//销售金额
    private double rate1;//费率
    private double plat_storage;//单拖储存成本
    private double plat_transport;//单拖配送成本
    private double plat_cost;
    private double order_cost;
    private double area_cost;

    public double getRate1() {
        return rate1;
    }

    public void setRate1(double rate1) {
        this.rate1 = rate1;
    }

    public double getPlat_cost() {
        return plat_cost;
    }

    public void setPlat_cost(double plat_cost) {
        this.plat_cost = plat_cost;
    }

    public double getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(double order_cost) {
        this.order_cost = order_cost;
    }

    public double getArea_cost() {
        return area_cost;
    }

    public void setArea_cost(double area_cost) {
        this.area_cost = area_cost;
    }

    public double getPlat_storage() {
        return plat_storage;
    }

    public void setPlat_storage(double plat_storage) {
        this.plat_storage = plat_storage;
    }

    public double getPlat_transport() {
        return plat_transport;
    }

    public void setPlat_transport(double plat_transport) {
        this.plat_transport = plat_transport;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getStorageCost() {
        return storageCost;
    }

    public void setStorageCost(double storageCost) {
        this.storageCost = storageCost;
    }

    public double getBuildCost() {
        return buildCost;
    }

    public void setBuildCost(double buildCost) {
        this.buildCost = buildCost;
    }

    public double getInventoryCost() {
        return inventoryCost;
    }



    public void setInventoryCost(double inventoryCost) {
        this.inventoryCost = inventoryCost;
    }

    public double getAll() {
        return all;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public void setAll(double all) {
        this.all = all;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getEmp() {
        return emp;
    }

    public void setEmp(double emp) {
        this.emp = emp;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCar() {
        return car;
    }

    public void setCar(double car) {
        this.car = car;
    }

    public double getOrder_rate() {
        return order_rate;
    }

    public void setOrder_rate(double order_rate) {
        this.order_rate = order_rate;
    }

    public double getReplenishment_rate() {
        return replenishment_rate;
    }

    public void setReplenishment_rate(double replenishment_rate) {
        this.replenishment_rate = replenishment_rate;
    }

    public double getDistribution_rate() {
        return distribution_rate;
    }

    public void setDistribution_rate(double distribution_rate) {
        this.distribution_rate = distribution_rate;
    }

    public double getStorage_area() {
        return storage_area;
    }

    public void setStorage_area(double storage_area) {
        this.storage_area = storage_area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getStorage_rate() {
        return storage_rate;
    }

    public void setStorage_rate(double storage_rate) {
        this.storage_rate = storage_rate;
    }

    public double getInventory_num() {
        return inventory_num;
    }

    public void setInventory_num(double inventory_num) {
        this.inventory_num = inventory_num;
    }

    public double getThroughput_num() {
        return throughput_num;
    }

    public void setThroughput_num(double throughput_num) {
        this.throughput_num = throughput_num;
    }

    public double getSales_account() {
        return sales_account;
    }

    public void setSales_account(double sales_account) {
        this.sales_account = sales_account;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
