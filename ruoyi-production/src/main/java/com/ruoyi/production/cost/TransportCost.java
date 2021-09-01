package com.ruoyi.production.cost;

/**
 * 运输成本
 */
public class TransportCost {

    private double transportCost;//运输成本

    private double distributionCost;//配送成本


    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(double distributionCost) {
        this.distributionCost = distributionCost;
    }
}
