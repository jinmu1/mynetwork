package com.ruoyi.warehousing.test;

import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.form.*;
import com.ruoyi.warehousing.process.*;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Tray;
import com.ruoyi.warehousing.resource.facilities.buffer.Park;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.resource.facilities.storage.Storage;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.utils.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WarehouseTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    @Test
    public void  getCar(){
        Platform platform = new Platform();
        int parking_num = platform.getParking_num("小车4米6",1000,0.5,8);
        double area = platform.getPlatformArea(parking_num,3.5,3);
        List<Supplier> suppliers = WarehousingUtil.initSupplier(100);
        List<Goods> list = WarehousingUtil.createGoodsMessage(1000, suppliers);
        List<Car> cars = WarehousingUtil.initCar(list, 1000, "小车4米6");
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, 3.5);
        List<Emp> emps = WarehousingUtil.initEmp(parking_num);
        Park park = new Park();
        park.initPoints(cars.size(), 2);

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Upload.work(cars, platforms, emps,park);
        for (EmpLog empLog:list1){
            distance +=empLog.getDistance();
        }
        System.out.println(distance);
    }

    @Test
    public void getTImes(){
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "18:00:00");//当前时间
        List<EmpLog> list = new ArrayList<>();
        HashMap<Date,List<Tray>> map = new HashMap<>();
        List<String> dates = DateUtils.getIntervalTimeList(sdf.format(runTime),sdf.format(endTime),(int)10*60/5);
        for(String str:dates){
            System.out.println(str);
        }
    }

    @Test
    public void  getTally(){
        Tally tally = new Tally();
        tally = tally.getTallyArea(1000, "小车4米6", 3, 3.5, 0.2);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse(), tally.getTally_longitudinal(), 3.5, 0.2, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(1000);
        List<Tray> trays = Tray.initTrays(list);
        ;
        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

        List<EmpLog> list1 = Tallying.work(tally,trays,1.1,emps,3);
        double distance = 0.0;
        double rate = 0.0;
        for (EmpLog empLog:list1){
            distance +=empLog.getDistance();
        }
        System.out.println(distance);
    }

    @Test
    public void getPutaway(){
        Storage storage = new Storage();
        storage = storage.getHightStorage(1000,12,3,0.1,1.5);
        List<Goods> list = WarehousingUtil.createGoods(1000);
        List<Cargo> cargos = WarehousingUtil.initCargos(list,40000,storage);
        storage.initStorage(storage,cargos);
        List<Emp> emps = WarehousingUtil.initEmp((int)(1000/50));
        List<Tray> trays = Tray.initTrays(list);
        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Putaway.work(storage,trays,1.1,emps);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }
        System.out.println(distance);
    }
    @Test
    public void getSorting(){
        Tally tally = new Tally();
        int orderLine = 5;
        tally = tally.getTallyArea(1000, "小车4米6", 3, 3, 0.2);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse()*orderLine, tally.getTally_longitudinal()*orderLine, 3, 0.2, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(1000*orderLine);
        List<Tray> trays = Tray.initTrays(list);
        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Sorting.work(tally,trays,1.1,emps,3,"按单分拣",7);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }
        System.out.println(distance);
    }

    @Test
    public void loading(){
        Platform platform = new Platform();
        int parking_num = platform.getParking_num("小车4米6", 5000, 0.5, 8);
        double area = platform.getPlatformArea(parking_num, 3.5, 3);
        List<Customer> coustomers = WarehousingUtil.initCustomer(200);

        List<Goods> list = WarehousingUtil.createGoodsDeliveryMessage(1000, coustomers);
        List<Car> cars = WarehousingUtil.initCar1(list, 1000, "小车4米6");
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, 3.5);
        List<Emp> emps = WarehousingUtil.initEmp(parking_num);
        Park park = new Park();
        park.initPoints(cars.size(), 2);

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Loading.work(cars, platforms, emps, park);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }
        System.out.println(distance);
    }
}
