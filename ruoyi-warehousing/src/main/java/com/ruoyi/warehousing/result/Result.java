package com.ruoyi.warehousing.result;

public class Result {
    private int batch;//批次
    private double cost;//成本
    private double distance;//行走距离
    private double sortdistance;//行走距离
    private double putawaydistance;//行走距离

    private int uploadEmp;//卸货人员数量
    private int tallyEmp;//理货能力
    private int putawayEmp;//上架人员
    private int takeDownEmp;//下架人员
    private int sortingEmp;//分拣人员
    private int deliveryEmp;//装车人员
    private double uploadRate;//卸货人员利用率
    private double putawayRate;//上架人员利用率
    private double takeDownEmpRate;//下架人员利用率
    private double sortingRate;//分拣人员利用率
    private double deliveryRate;//装车人员利用率
    private double forklift;//卸货叉车
    private double empCost;//人员成本
    private double forkliftCost;//设备成本
    private int carNum;
    private double deliveryArea;//出库理货面积

    private double sortingArea;//分拣区面积
    private double platformNum;//卸货月台数量
    private double platformArea;//卸货月台面积
    private double platformRate;//卸货月台利用率
    private double tallyArea;//理货区面积
    private double tallyRate;//理货区面积利用率
    private double storageArea;//存储区面积
    private double tally1Area;//分拣区面积
    private double tally1Rate;//分拣区面积利用率
    private double platform1Num;//装车月台数量
    private double platform1Area;//装车月台面积
    private double platform1Rate;//装车月台利用率

    private int cargo;//货位数量

    public double getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(double deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public int getCargo() {
        return cargo;
    }

    public double getSortingArea() {
        return sortingArea;
    }

    public void setSortingArea(double sortingArea) {
        this.sortingArea = sortingArea;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
    public Result() {
        super();
    }
    public Result(int batch) {
        this.batch = batch;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getTakeDownEmp() {
        return takeDownEmp;
    }

    public void setTakeDownEmp(int takeDownEmp) {
        this.takeDownEmp = takeDownEmp;
    }

    public double getTakeDownEmpRate() {
        return takeDownEmpRate;
    }

    public void setTakeDownEmpRate(double takeDownEmpRate) {
        this.takeDownEmpRate = takeDownEmpRate;
    }

    public Result(int batch, double cost, double distance, int uploadEmp, int putawayEmp, int sortingEmp, int deliveryEmp, double uploadRate, double putawayRate, double sortingRate, double deliveryRate, double platformNum, double platformArea, double platformRate, double tallyArea, double tallyRate, double storageArea, double tally1Area, double tally1Rate, double platform1Num, double platform1Area, double platform1Rate) {
        this.batch = batch;
        this.cost = cost;
        this.distance = distance;
        this.uploadEmp = uploadEmp;
        this.putawayEmp = putawayEmp;
        this.sortingEmp = sortingEmp;
        this.deliveryEmp = deliveryEmp;
        this.uploadRate = uploadRate;
        this.putawayRate = putawayRate;
        this.sortingRate = sortingRate;
        this.deliveryRate = deliveryRate;
        this.platformNum = platformNum;
        this.platformArea = platformArea;
        this.platformRate = platformRate;
        this.tallyArea = tallyArea;
        this.tallyRate = tallyRate;
        storageArea = storageArea;
        this.tally1Area = tally1Area;
        this.tally1Rate = tally1Rate;
        this.platform1Num = platform1Num;
        this.platform1Area = platform1Area;
        this.platform1Rate = platform1Rate;
    }

    public double getSortdistance() {
        return sortdistance;
    }

    public void setSortdistance(double sortdistance) {
        this.sortdistance = sortdistance;
    }

    public double getPutawaydistance() {
        return putawaydistance;
    }

    public void setPutawaydistance(double putawaydistance) {
        this.putawaydistance = putawaydistance;
    }

    public int getTallyEmp() {
        return tallyEmp;
    }

    public void setTallyEmp(int tallyEmp) {
        this.tallyEmp = tallyEmp;
    }

    public double getForklift() {
        return forklift;
    }

    public void setForklift(double forklift) {
        this.forklift = forklift;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getUploadEmp() {
        return uploadEmp;
    }

    public void setUploadEmp(int uploadEmp) {
        this.uploadEmp = uploadEmp;
    }

    public double getEmpCost() {
        return empCost;
    }

    public void setEmpCost(double empCost) {
        this.empCost = empCost;
    }

    public double getForkliftCost() {
        return forkliftCost;
    }

    public void setForkliftCost(double forkliftCost) {
        this.forkliftCost = forkliftCost;
    }

    public int getPutawayEmp() {
        return putawayEmp;
    }

    public void setPutawayEmp(int putawayEmp) {
        this.putawayEmp = putawayEmp;
    }

    public int getSortingEmp() {
        return sortingEmp;
    }

    public void setSortingEmp(int sortingEmp) {
        this.sortingEmp = sortingEmp;
    }

    public int getDeliveryEmp() {
        return deliveryEmp;
    }

    public void setDeliveryEmp(int deliveryEmp) {
        this.deliveryEmp = deliveryEmp;
    }

    public double getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(double uploadRate) {
        this.uploadRate = uploadRate;
    }

    public double getPutawayRate() {
        return putawayRate;
    }

    public void setPutawayRate(double putawayRate) {
        this.putawayRate = putawayRate;
    }

    public double getSortingRate() {
        return sortingRate;
    }

    public void setSortingRate(double sortingRate) {
        this.sortingRate = sortingRate;
    }

    public double getDeliveryRate() {
        return deliveryRate;
    }

    public void setDeliveryRate(double deliveryRate) {
        this.deliveryRate = deliveryRate;
    }

    public double getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(double platformNum) {
        this.platformNum = platformNum;
    }

    public double getPlatformArea() {
        return platformArea;
    }

    public void setPlatformArea(double platformArea) {
        this.platformArea = platformArea;
    }

    public double getPlatformRate() {
        return platformRate;
    }

    public void setPlatformRate(double platformRate) {
        this.platformRate = platformRate;
    }

    public double getTallyArea() {
        return tallyArea;
    }

    public void setTallyArea(double tallyArea) {
        this.tallyArea = tallyArea;
    }

    public double getTallyRate() {
        return tallyRate;
    }

    public void setTallyRate(double tallyRate) {
        this.tallyRate = tallyRate;
    }

    public double getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(double storageArea) {
        this.storageArea = storageArea;
    }

    public double getTally1Area() {
        return tally1Area;
    }

    public void setTally1Area(double tally1Area) {
        this.tally1Area = tally1Area;
    }

    public double getTally1Rate() {
        return tally1Rate;
    }

    public void setTally1Rate(double tally1Rate) {
        this.tally1Rate = tally1Rate;
    }

    public double getPlatform1Num() {
        return platform1Num;
    }

    public void setPlatform1Num(double platform1Num) {
        this.platform1Num = platform1Num;
    }

    public double getPlatform1Area() {
        return platform1Area;
    }

    public void setPlatform1Area(double platform1Area) {
        this.platform1Area = platform1Area;
    }

    public double getPlatform1Rate() {
        return platform1Rate;
    }

    public void setPlatform1Rate(double platform1Rate) {
        this.platform1Rate = platform1Rate;
    }
}
