package com.ruoyi.warehousing.action;

import com.ruoyi.warehousing.form.*;
import com.ruoyi.warehousing.process.*;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.equipment.LightStorage;
import com.ruoyi.warehousing.resource.equipment.Tray;
import com.ruoyi.warehousing.resource.facilities.buffer.Park;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.resource.facilities.storage.Storage;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.result.Result;
import com.ruoyi.warehousing.utils.AreaUtils;
import com.ruoyi.warehousing.utils.DateUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Action {
    private static Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
    private static Date startTime = new Date();//开始时间
    private static Date endTime = new Date();//工作结束时间

    /**
     * 月台卸货初始化类
     *
     * @param carLength               车辆类型
     * @param transportNum            运输托数
     * @param unloading_time          卸货车辆数
     * @param everyDay_unloading_time 每日卸货时间
     * @param platform_width          月台宽度
     * @param platform_length         月台长度
     * @param supplier                供应商数量
     * @param goods_num               物料数量
     * @param utilization             最大利用率
     * @return
     */
    public static Result runPlatform(String carLength, double transportNum, double unloading_time, double everyDay_unloading_time, double platform_width, double platform_length, double supplier, double goods_num, double utilization,double batch,double platform_a) {
        Platform platform = new Platform();
        int parking_num = platform.getParking_num(carLength, transportNum, unloading_time, everyDay_unloading_time,batch); //计算月台数量
        double area = platform.getPlatformArea(parking_num, platform_width, platform_length);
        List<Supplier> suppliers = WarehousingUtil.initSupplier(supplier);
        List<Goods> list = WarehousingUtil.createGoodsMessage(goods_num, suppliers);
        List<Car> cars = WarehousingUtil.initCar(list, transportNum, carLength);
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, platform_width);
        List<Emp> emps = WarehousingUtil.initEmp(10);
        Park park = new Park();
        park.initPoints(cars.size(), 2);
        Result result = new Result();
        result.setCarNum(cars.size());
        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Upload.work(cars, platforms, emps, park,platform_a);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }


        result.setPlatformArea(area);
        result.setPlatformNum(parking_num);
        result.setDistance(distance);

        result.setUploadRate(rate / 10 / 60 / 60 / emps.size());
        int emp = (int) Math.ceil(emps.size() * everyDay_unloading_time / 8);
        int emp1 = (int) Math.ceil(emps.size() * everyDay_unloading_time / 8 * result.getUploadRate() / (utilization / 100));
        result.setUploadEmp(emp1+(int)Math.ceil(batch*3));
        result.setUploadRate(emp * result.getUploadRate() / emp1);
        return result;
    }

    /**
     * @param transportNum 理货总托盘量
     * @param batch 理货批次
     * @param tallyEmpCapacity 理货行走速度
     * @param tally_channel 理货通道
     * @param tray_clearance
     * @return
     */
    public static Result getTally(double transportNum, String carLength, double batch, double tallyEmpCapacity, double tally_channel, double tray_clearance,double utilization,double tallyTime,double goods_num) {
        Tally tally = new Tally();
        tally = tally.getTallyArea(transportNum, carLength, batch, tally_channel, tray_clearance);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse(), tally.getTally_longitudinal(), tally_channel, tray_clearance, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(goods_num);
        List<Tray> trays = Tray.initTrays(list,transportNum);

        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Tallying.work(tally,trays,tallyEmpCapacity,emps,batch,tallyTime);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }

        Result result = new Result();
        result.setTallyArea(tally.getArea());
        result.setDistance(distance);
        result.setTallyRate(rate /10/60/60/emps.size());
        int emp = (int) Math.ceil(emps.size() );
        int emp1 = (int) Math.ceil(emps.size()*result.getTallyRate() / (utilization / 100));
        result.setTallyEmp(emp1+(int)Math.sqrt(batch*4));
        result.setTallyRate(emp * result.getTallyRate() / emp1);
        return result;
    }

    public static Result getPutaway(double putawayNum, double storageNum, double height, double forklift_channel, double utilization, double putaway_speed, double shelf_space, double shelf_height) {
        Storage storage = new Storage();
        storage = storage.getHightStorage(storageNum,height,forklift_channel,shelf_space,shelf_height);
        List<Goods> list = WarehousingUtil.createGoods(putawayNum);
        List<Cargo> cargos = WarehousingUtil.initCargos(list,storageNum,storage);
        storage.initStorage(storage,list);
        List<Emp> emps = WarehousingUtil.initEmp((int)(putawayNum/50));
        List<Tray> trays = Tray.initTrays(list,putawayNum);
        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Putaway.work(storage,trays,putaway_speed,emps);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }

        Result result = new Result();
        result.setStorageArea(storage.getArea());
        result.setDistance(distance);
        result.setCargo(storage.getCargo());
        result.setPutawayRate(rate / 10 / 60 / 60 / emps.size());
        int emp = (int) Math.ceil(emps.size() );
        int emp1 = (int) Math.ceil(emps.size()*result.getPutawayRate() / (utilization / 100));
        result.setPutawayEmp(emp1);
        result.setPutawayRate(emp * result.getPutawayRate() / emp1);
        return result;
    }

    public static Result getStorage(double storageNum, double height, double forklift_channel,  double shelf_space, double shelf_height) {
        Storage storage = new Storage();
        storage = storage.getHightStorage(storageNum,height,forklift_channel,shelf_space,shelf_height);
        Result result = new Result();
        result.setStorageArea(storage.getArea());
        result.setCargo(storage.getCargo());
        return result;
    }

    public static Result getTakeDown(double take_downNum, double storageNum, double height, double forklift_channel, double utilization, double putaway_speed, double shelf_space, double shelf_height) {
        Storage storage = new Storage();
        storage = storage.getHightStorage(storageNum,height,forklift_channel,shelf_space,shelf_height);
        List<Goods> list = WarehousingUtil.createGoods(take_downNum);
        List<Cargo> cargos = WarehousingUtil.initCargos(list,storageNum,storage);
        storage.initStorage(storage,list);
        List<Emp> emps = WarehousingUtil.initEmp((int)(take_downNum/50));
        List<Tray> trays = Tray.initTrays(list,take_downNum);
        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = TakeDown.work(storage,trays,putaway_speed,emps);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }

        Result result = new Result();
        result.setStorageArea(storage.getArea());
        result.setDistance(distance);
        result.setCargo(storage.getCargo());
        result.setTakeDownEmpRate(rate / 10 / 60 / 60 / emps.size());
        int emp = (int) Math.ceil(emps.size() );
        int emp1 = (int) Math.ceil(emps.size()*result.getTakeDownEmpRate() / (utilization / 100));
        result.setTakeDownEmp(emp1);
        result.setTakeDownEmpRate(emp * result.getTakeDownEmpRate() / emp1);
        return result;
    }

    public static Result getSorting(double transportNum, double batch, int orderLine, double sortingSpeed, double tally_channel, double tray_clearance, double utilization,String sort_type,double goods_num,double sortingTime) {
        Tally tally = new Tally();
        tally = tally.getTallyArea(transportNum, "小车4米6", batch, tally_channel, tray_clearance);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse()*orderLine, tally.getTally_longitudinal()*orderLine, tally_channel, tray_clearance, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(goods_num);
        List<Tray> trays = Tray.initTrays(list,transportNum*orderLine);
        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Sorting.work(tally,trays,sortingSpeed,emps,batch,sort_type,orderLine/2,sortingTime);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }

        Result result = new Result();
        result.setSortingArea(tally.getArea());
        result.setDistance(distance);
        result.setSortingRate(rate / 10 / 60 / 60 / emps.size());
        int emp = (int) Math.ceil(emps.size() );
        int emp1 = (int) Math.ceil(emps.size()*result.getSortingRate() / (utilization / 100));
        result.setSortingEmp(emp1+(int)Math.sqrt(batch));
        result.setSortingRate(emp * result.getSortingRate() / emp1);
        return result;
    }

    public static Result getDelivery(double deliveryNum, double batch, double delivery_speed, double tally_channel, double tray_clearance, double utilization,double delivery_Time) {
        Tally tally = new Tally();
        tally = tally.getTallyArea(deliveryNum, "小车4米6", batch, tally_channel, tray_clearance);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse(), tally.getTally_longitudinal(), tally_channel, tray_clearance, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(150);
        List<Tray> trays = Tray.initTrays(list,deliveryNum);

        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Delivery.work(tally,trays,delivery_speed,emps,batch,delivery_Time);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }

        Result result = new Result();
        result.setDeliveryArea(tally.getArea());
        result.setDistance(distance);
        result.setDeliveryRate(rate /10/60/60/emps.size());
        int emp = (int) Math.ceil(emps.size() );
        int emp1 = (int) Math.ceil(emps.size()*result.getDeliveryRate() / (utilization / 100));
        result.setDeliveryEmp(emp1+(int)batch);
        result.setDeliveryRate(emp * result.getDeliveryRate() / emp1);
        return result;

    }

    public static Result runLoading(String carLength, double transportNum, double unloading_time, double everyDay_unloading_time, double platform_width, double platform_length, double customer, double goods_num, double utilization,double platform_a) {
        Platform platform = new Platform();
        int parking_num = platform.getParking_num(carLength, transportNum, unloading_time, everyDay_unloading_time,2);
        double area = platform.getPlatformArea(parking_num, platform_width, platform_length);
        List<Customer> coustomers = WarehousingUtil.initCustomer(customer);
        List<Goods> list = WarehousingUtil.createGoodsDeliveryMessage(goods_num, coustomers);
        List<Car> cars = WarehousingUtil.initCar1(list, transportNum, carLength);
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, platform_length);
        List<Emp> emps = WarehousingUtil.initEmp(parking_num);
        Park park = new Park();
        park.initPoints(cars.size(), 2);
        Result result = new Result();
        result.setCarNum(cars.size());
        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Loading.work(cars, platforms, emps, park,platform_a);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }


        result.setPlatformArea(area);
        result.setPlatformNum(parking_num);
        result.setDistance(distance);

        result.setUploadRate(rate / 12 / 60 / 60 / emps.size());
        int emp = (int) Math.ceil(emps.size() * everyDay_unloading_time / 8);
        int emp1 = (int) Math.ceil(emps.size() * everyDay_unloading_time / 8 * result.getUploadRate() / (utilization / 100));
        result.setUploadEmp(emp1);
        result.setUploadRate(emp * result.getUploadRate() / emp1);
        return result;

    }
}
