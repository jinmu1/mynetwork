package com.ruoyi.production.network;

import com.ruoyi.production.form.City;
import com.ruoyi.production.resource.facilities.productLine.ProductionLine;

import java.util.List;

public class Factory {
    private City city;
    private double area;
    private List<ProductionLine> productionLines;



    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<ProductionLine> getProductionLines() {
        return productionLines;
    }

    public void setProductionLines(List<ProductionLine> productionLines) {
        this.productionLines = productionLines;
    }
}
